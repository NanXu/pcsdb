NTSB数据库原始数据处理方案

1、首先从narratives表中匹配本期需要的数据，将ev_id作为临时表进行存储
create table ev_id AS
SELECT COUNT(ev_id) FROM narratives WHERE  (
	narr_accf LIKE '%low temperature%'
	OR narr_accf LIKE '%turbulence%'
	OR narr_accf LIKE '%visibility%'
	OR narr_accf LIKE '%bad weather%'
	OR narr_accf LIKE '%nimbus%'
	OR narr_accf LIKE '%wind shear%'
	OR narr_accf LIKE '%gusty windr%'
	OR narr_accf LIKE '%overcast%'
	OR narr_accf LIKE '%precipitation%'
	OR narr_accf LIKE '%freezing%'
	OR narr_accf LIKE '%ice%'
	OR narr_accf LIKE '%convective weather%'
	OR narr_accf LIKE '%low ceiling%'
	OR narr_accf LIKE '%obscuration%'
	OR narr_accf LIKE '%lightning%'
	OR narr_accf LIKE '%thunderstorm%'
	OR narr_accf LIKE '%icing%'
	OR narr_accf LIKE '%rain%'
	OR narr_accf LIKE '%moisture%'
	OR narr_accf LIKE '%fog%'
	OR narr_accf LIKE '%snow%'
	OR narr_accf LIKE '%wet%'
	OR narr_accf LIKE '%crosswind%'
	OR narr_accf LIKE '%freezing rain%'
	OR narr_accf LIKE '%cold%'
) OR (
	narr_cause LIKE '%low temperature%'
	OR narr_cause LIKE '%turbulence%'
	OR narr_cause LIKE '%visibility%'
	OR narr_cause LIKE '%bad weather%'
	OR narr_cause LIKE '%nimbus%'
	OR narr_cause LIKE '%wind shear%'
	OR narr_cause LIKE '%gusty windr%'
	OR narr_cause LIKE '%overcast%'
	OR narr_cause LIKE '%precipitation%'
	OR narr_cause LIKE '%freezing%'
	OR narr_cause LIKE '%ice%'
	OR narr_cause LIKE '%convective weather%'
	OR narr_cause LIKE '%low ceiling%'
	OR narr_cause LIKE '%obscuration%'
	OR narr_cause LIKE '%lightning%'
	OR narr_cause LIKE '%thunderstorm%'
	OR narr_cause LIKE '%icing%'
	OR narr_cause LIKE '%rain%'
	OR narr_cause LIKE '%moisture%'
	OR narr_cause LIKE '%fog%'
	OR narr_cause LIKE '%snow%'
	OR narr_cause LIKE '%wet%'
	OR narr_cause LIKE '%crosswind%'
	OR narr_cause LIKE '%freezing rain%'
	OR narr_cause LIKE '%cold%'
) OR (
	narr_accp LIKE '%low temperature%'
	OR narr_accp LIKE '%turbulence%'
	OR narr_accp LIKE '%visibility%'
	OR narr_accp LIKE '%bad weather%'
	OR narr_accp LIKE '%nimbus%'
	OR narr_accp LIKE '%wind shear%'
	OR narr_accp LIKE '%gusty windr%'
	OR narr_accp LIKE '%overcast%'
	OR narr_accp LIKE '%precipitation%'
	OR narr_accp LIKE '%freezing%'
	OR narr_accp LIKE '%ice%'
	OR narr_accp LIKE '%convective weather%'
	OR narr_accp LIKE '%low ceiling%'
	OR narr_accp LIKE '%obscuration%'
	OR narr_accp LIKE '%lightning%'
	OR narr_accp LIKE '%thunderstorm%'
	OR narr_accp LIKE '%icing%'
	OR narr_accp LIKE '%rain%'
	OR narr_accp LIKE '%moisture%'
	OR narr_accp LIKE '%fog%'
	OR narr_accp LIKE '%snow%'
	OR narr_accp LIKE '%wet%'
	OR narr_accp LIKE '%crosswind%'
	OR narr_accp LIKE '%freezing rain%'
	OR narr_accp LIKE '%cold%'
)

2、aircraft表中far_part = '121'的数据

CREATE TABLE air_ev_id AS
SELECT
	ev_id
FROM
	aircraft
WHERE
	far_part = '121'
AND EXISTS (
	SELECT
		aircraft.ev_id
	FROM
		ev_id
	WHERE ev_id = aircraft.ev_id
)
3、入库语句

INSERT INTO ntsb_events (
	ev_id,
	ev_type,
	ev_date,
	ev_time,
	ev_city,
	wx_cond_basic,
	inj_tot_f,
	inj_tot_s,
	inj_tot_m,
	inj_tot_n,
	STATUS
) SELECT
	ev_id,
	ev_type,
	ev_date,
	ev_time,
	ev_city,
	wx_cond_basic,
	inj_tot_f,
	inj_tot_s,
	inj_tot_m,
	inj_tot_n,
	'0'
FROM
	ntsb.`events`
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ev_id = ntsb.`events`.ev_id
	)
/*ntsb_aircraft*/
INSERT INTO ntsb_aircraft (
	ev_id,
	ntsb_no,
	dprt_apt_id,
	dest_apt_id,
	damage,
	acft_fire,
	acft_model,
	acft_make,
	regis_no,
	num_eng,
	oper_name,
	far_part,
	afm_hrs,
	type_last_insp,
	afm_hrs_last_insp,
	cert_max_gr_wt,
	evacuation
) SELECT
	ev_id,
	ntsb_no,
	dprt_apt_id,
	dest_apt_id,
	damage,
	acft_fire,
	acft_model,
	acft_make,
	regis_no,
	num_eng,
	oper_name,
	far_part,
	afm_hrs,
	type_last_insp,
	afm_hrs_last_insp,
	cert_max_gr_wt,
	evacuation
FROM
	ntsb.aircraft
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ntsb.air_ev_id.ev_id = ntsb.aircraft.ev_id
	)

	
INSERT INTO ntsb_engines (
	ev_id,
	eng_mfgr,
	eng_model,
	eng_no
) SELECT
	ev_id,
	eng_mfgr,
	eng_model,
	eng_no
FROM
	ntsb.`engines`
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ntsb.air_ev_id.ev_id = ntsb.`engines`.ev_id
	)
	
INSERT INTO ntsb_events_sequence (ev_id, phase_no, eventsoe_no) 
SELECT
	ev_id,
	phase_no,
	eventsoe_no
FROM
	ntsb.Events_Sequence
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ntsb.air_ev_id.ev_id = ntsb.Events_Sequence.ev_id
	)
	
INSERT INTO ntsb_findings (
	ev_id,
	category_no,
	subcategory_no,
	section_no,
	subsection_no,
	modifier_no
) SELECT
	ev_id,
	category_no,
	subcategory_no,
	section_no,
	subsection_no,
	modifier_no
FROM
	ntsb.Findings
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ntsb.air_ev_id.ev_id = ntsb.Findings.ev_id
	)
	
INSERT INTO ntsb_injury (
	ev_id,
	injury_level,
	inj_person_category,
	inj_person_count
) SELECT
	ev_id,
	injury_level,
	inj_person_category,
	inj_person_count
FROM
	ntsb.injury
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ntsb.air_ev_id.ev_id = ntsb.injury.ev_id
	)
	
INSERT INTO ntsb_narratives (
	ev_id,
	narr_accf,
	narr_cause,
	narr_accp
) SELECT
	ev_id,
	narr_accf,
	narr_cause,
	narr_accp
FROM
	ntsb.narratives
WHERE
	EXISTS (
		SELECT
			1
		FROM
			ntsb.air_ev_id
		WHERE
			ntsb.air_ev_id.ev_id = ntsb.narratives.ev_id
	)
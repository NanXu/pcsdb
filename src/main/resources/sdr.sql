-- 天气数据库 2018-11-22--
INSERT INTO edu_pcsdb_climate.sdr_raw_data (
	id,
	seqNumber,
	receiveDate,
	seq,
	regionCode,
	occurrenceDate,
	ataCode,
	partNumber,
	partName,
	partManufacturerName,
	partManufacturerModal,
	aircraftManufacturerName,
	aircraftManufacturerCode,
	aircraftRegion,
	engineManufacturerName,
	engineManufacturerModel,
	failedPartDesc,
	operatorCode,
	preCodeFirst,
	preCodeSecond,
	preDesc,
	measureRemark,
	natureConditionDesc,
	operationCode,
	aircraftRegistrationNumber,
	operationText,
	numberOfEngines,
	remark,
	maintain
) SELECT
	c18,
	c5,
	c10,
	c12,
	c14,
	c25,
	c40,
	c90,
	c100,
	c110,
	c120,
	c130,
	c150,
	c160,
	c170,
	c190,
	c260,
	c300,
	c310a,
	c310c,
	c314a,
	c320a,
	c324a,
	c330,
	c390,
	c332,
	c420,
	c510a,
	c510b
FROM
	edu_pcsdb_sdr.sdr_source_1995
WHERE
	c20 = '1'
AND c35 = 'A'
AND (
	c510a LIKE '%low temperature%'
	OR c510a LIKE '%turbulence%'
	OR c510a LIKE '%visibility%'
	OR c510a LIKE '%bad weather%'
	OR c510a LIKE '%nimbus%'
	OR c510a LIKE '%wind shear%'
	OR c510a LIKE '%gusty windr%'
	OR c510a LIKE '%overcast%'
	OR c510a LIKE '%precipitation%'
	OR c510a LIKE '%freezing%'
	OR c510a LIKE '%ice%'
	OR c510a LIKE '%convective weather%'
	OR c510a LIKE '%low ceiling%'
	OR c510a LIKE '%obscuration%'
	OR c510a LIKE '%lightning%'
	OR c510a LIKE '%thunderstorm%'
	OR c510a LIKE '%icing%'
	OR c510a LIKE '%rain%'
	OR c510a LIKE '%moisture%'
	OR c510a LIKE '%fog%'
	OR c510a LIKE '%snow%'
	OR c510a LIKE '%wet%'
	OR c510a LIKE '%crosswind%'
	OR c510a LIKE '%freezing rain%'
	OR c510a LIKE '%cold%'
)
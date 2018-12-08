/**
 * 表单验证方法
 * 
 * @param containerId
 * 				form的ID
 */
function validateForm(containerId){
    var valid = $(containerId).validationEngine({returnIsValid: true, showOnMouseOver:false});
	return valid;
} 
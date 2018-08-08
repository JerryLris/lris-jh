jQuery.validator.addMethod("mobile", function(value, element) {
var length = value.length;
return this.optional(element) || (length == 11 && /^(13|14|15|16|17|18|19)[0-9]{9}$/.test(value));
}, "手机号码格式错误!");
jQuery.validator.addMethod("chcharacter", function(value, element) {
var tel = /^[\u4e00-\u9fa5]+$/;
return this.optional(element) || (tel.test(value));
}, "请输入汉字");
jQuery.validator.addMethod("alnum", function(value, element) {
return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");
jQuery.validator.addMethod("alnumber", function(value, element) {
	return this.optional(element) || /^[0-9]+$/.test(value);
}, "只能输入数字");
jQuery.validator.addMethod("alphabet", function(value, element) {
return this.optional(element) || /^[a-zA-Z]+$/.test(value);
}, "只能输入英文字母");
jQuery.validator.addMethod("money", function(value, element) {
	return this.optional(element) || /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/.test(value);
}, "只能输入正确的金额，并且小数后最多两位");
jQuery.validator.addMethod("rate", function(value, element) {
	return this.optional(element) || /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,6})?$/.test(value);
}, "只能输入正确的费率，并且小数后最多6位");

export const checkEmail = (email) => {
  var emailR = /^\w+@\w+\.\w+$/i;
  if (!emailR.test(email)) {
    return false;
  }
  return true;
};
export const checkPassword = (password) => {
  var numR = /[0-9]/;
  var alphaLowR = /[a-z]/;
  var alphaUppR = /[A-Z]/;
  var otherSymbolR = /[-_^&]/;
  var strength = 0;
  if (password.length < 6 || password.length > 16) {
    // printPasswordPrompt("密码长度应该至少达到8个字符。",strength);
    // printRequestErrorInfo("ddddddddddddddd");
    return false;
  } else {
    if (numR.test(password)) strength++;
    if (alphaUppR.test(password)) strength++;
    if (alphaLowR.test(password)) strength++;
    if (otherSymbolR.test(password)) strength++;
    // printPasswordPrompt("密码强度:"+strength,strength);
    return true;
  }
};

export function checkFileFormat(fileName) {
  if (fileName.endsWith(".txt") || fileName.endsWith(".docx")) {
    return true;
  } else return false;
}

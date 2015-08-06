package forgetPwHelper;

import domain.usrinfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成帐户激活、重新设置密码的链接
 */
public class GenerateLinkUtils {

    private static final String CHECK_CODE = "checkCode";

    /**
     * 生成帐户激活链接
     */
    public static String generateActivateLink(final usrinfo user) {
		return "http://202.202.5.134:8080/ubir2New/Login"+ CHECK_CODE + "=" + generateCheckcode(user);
 /*       return "http://localhost:8080/ubir2New/Login"
                + user.getUsrName() + "&" + CHECK_CODE + "=" + generateCheckcode(user);*/
    	
    }

    /**
     * 生成重设密码的链接
     */
    public static String generateResetPwdLink(final usrinfo user) {
		return "http://202.202.5.134:8080/ubir2New/Link2find" ;
      /*  return "http://localhost:8080/ubir2New/UserOperation=modifyPw"
                + user.getUsrName() + "&" + CHECK_CODE + "=" + generateCheckcode(user);*/
    }

    /**
     * 生成验证帐户的MD5校验码
     *
     * @param user 要激活的帐户
     * @return 将loginName和pwd组合后，通过md5加密后的16进制格式的字符串
     */
    public static String generateCheckcode(final usrinfo user) {
        String loginName = user.getUsrName();
        String pwd = user.getUsrPw();
        return md5(loginName + ":" + pwd);
    }

    /**
     * @param user      要激活的帐户
     * @param checkCode 注册时发送的校验码
     * @return 如果一致返回true，否则返回false
     * @Todo 验证校验码是否和注册时发送的验证码一致
     */
    public static boolean verifyCheckcode(final usrinfo user, final String checkCode) {
        return generateCheckcode(user).equals(checkCode);
    }

    private static String md5(final String string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] >= 0 && byteArray[i] < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }

}

package com.kk.realm;

import com.kk.pojo.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2018/4/21.
 * 将password 进行加密处理
 */
public class PasswordHelper {
    private static String algorithmName = "md5";
    private static final int hashIterations = 2;

    public static void encryptPassword(User user) {
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUsername()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}

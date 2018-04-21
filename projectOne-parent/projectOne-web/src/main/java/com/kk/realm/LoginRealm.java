package com.kk.realm;


import com.kk.pojo.Role;
import com.kk.pojo.User;
import com.kk.service.RoleService;
import com.kk.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacey on 2017/6/30.
 */

public class LoginRealm extends AuthorizingRealm{

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     *     当调用权限验证时，就会调用此方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) getAvailablePrincipal(principalCollection);

        Role role = null;

        try {
            User user = userloginService.findUserByName(username);
            //获取角色对象
            role = roleService.findRoleById(user.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> r = new HashSet<String>();
        if (role != null) {
            r.add(role.getRolename());
            info.setRoles(r);
        }

        return info;
    }

    /**
     * 在这个方法中，进行身份验证
     *    login时调用
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的，本例中是：org.apache.shiro.authc.UsernamePasswordToken@33799a1e
        //用户名
        String username = (String) token.getPrincipal();

        User user = null;
        try {
            user = userloginService.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user!=null){
            /**
             * get from database
             * Paremeter one: username
             * Paremeter two: password
             * Paremeter three: nick name;
             */
            SimpleAuthenticationInfo aInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
            aInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUsername()));
            this.setAuthenticationSession(user.getUsername());
            return aInfo;
        }
        return null;

    }
    /**
     * 将一些数据放到ShiroSession中，以便于其它地方使用
     * 比如Controller里面，使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setAuthenticationSession(Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            session.setAttribute("currentUser", value);
        }
    }
}

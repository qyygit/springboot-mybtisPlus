package com.atguigu.boot.jwt;

import com.atguigu.boot.constants.SystemConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author helen
 * @since 2019/10/16
 */
public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;
    public static final String APP_SECRET = "shooin!Q@W#E$R";
    //public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    public static String getJwtToken(JwtUserInfo jwtUserInfo){

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //.setSubject("guli-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", jwtUserInfo.getId())
                .claim("account", jwtUserInfo.getAccount())
                .claim("name", jwtUserInfo.getName())
                .claim("wareHouseId", jwtUserInfo.getWareHouseId())
                .claim("pftype", jwtUserInfo.getPftype())
                .claim("simi", jwtUserInfo.getSimi())
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取仓库id
     * @param request
     * @return
     */
    public static String getWareHouseIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(SystemConstants.AUTHORIZE_TOKEN);
        if(StringUtils.isEmpty(jwtToken))
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return  claims.get("wareHouseId") == null ? "" : claims.get("wareHouseId").toString();
    }

    /**
     * 根据token获取仓库id
     * @param request
     * @return
     */
    public static String getSimiByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(SystemConstants.AUTHORIZE_TOKEN);
        if(StringUtils.isEmpty(jwtToken))
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return claims.get("simi") == null ? "" : claims.get("simi").toString();
    }

    /**
     * 根据token获取会员id
     * @param request
     * @return
     */
    public static Long getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken))
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (Long)claims.get("id");
    }

    /**
     * 根据token获取jwtUserInfo信息
     * @param jwtToken
     * @return
     */
    public static JwtUserInfo getJwtUserInfoByJwtToken( String jwtToken) {
       // String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken))
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        JwtUserInfo jwtUserInfo = new JwtUserInfo();
        String id = claims.get("id") == null ? "" : claims.get("id").toString();
        String account = claims.get("account") == null ? "" : claims.get("account").toString();
        String name = claims.get("name") == null ? "" : claims.get("name").toString();
        String wareHouseId = claims.get("wareHouseId") == null ? "" : claims.get("wareHouseId").toString();
        String pftype = claims.get("pftype") == null ? "" : claims.get("pftype").toString();
        String simi = claims.get("simi") == null ? "" : claims.get("simi").toString();

        if(StringUtils.isNotEmpty(id))  jwtUserInfo.setId(id);
        jwtUserInfo.setAccount(account);
        jwtUserInfo.setName(name);
        jwtUserInfo.setWareHouseId(wareHouseId);
        jwtUserInfo.setSimi(simi);
        if(StringUtils.isNotEmpty(pftype))  jwtUserInfo.setPftype(Integer.valueOf(pftype));
        return jwtUserInfo;
    }

    /**
     * 根据token获取jwtUserInfo信息
     * @param request
     * @return
     */
    public static JwtUserInfo getJwtUserInfoByReq( HttpServletRequest request) {
        String jwtToken = request.getHeader(SystemConstants.AUTHORIZE_TOKEN);
        if(StringUtils.isEmpty(jwtToken))
            return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        JwtUserInfo jwtUserInfo = new JwtUserInfo();
        String id = claims.get("id") == null ? "" : claims.get("id").toString();
        String account = claims.get("account") == null ? "" : claims.get("account").toString();
        String name = claims.get("name") == null ? "" : claims.get("name").toString();
        String wareHouseId = claims.get("wareHouseId") == null ? "" : claims.get("wareHouseId").toString();
        String pftype = claims.get("pftype") == null ? "" : claims.get("pftype").toString();
        String simi = claims.get("simi") == null ? "" : claims.get("simi").toString();

        if(StringUtils.isNotEmpty(id))  jwtUserInfo.setId(id);
        jwtUserInfo.setAccount(account);
        jwtUserInfo.setName(name);
        jwtUserInfo.setWareHouseId(wareHouseId);
        jwtUserInfo.setSimi(simi);
        if(StringUtils.isNotEmpty(pftype))  jwtUserInfo.setPftype(Integer.valueOf(pftype));
        return jwtUserInfo;
    }
    public static void main(String[] args) {
        /*JwtUserInfo userInfo = new JwtUserInfo();
        userInfo.setId(3L);
        userInfo.setName("gl");
        userInfo.setAccount("T-guol");
        userInfo.setPftype(5);
        String jwtToken = JwtUtils.getJwtToken(userInfo);
        System.out.println(jwtToken);


        boolean b = JwtUtils.checkToken(jwtToken);
        System.out.println(b);


        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        Long a = (Long)claims.get("id");
        System.out.println(a);

        String account = (String)claims.get("account");
        System.out.println(account);
        String name = (String)claims.get("name");
        System.out.println(name);*/

        JwtUserInfo jwtUserInfo = new JwtUserInfo();
        jwtUserInfo.setAccount("admin");
        jwtUserInfo.setPftype(1);//安卓登录
        String jwtToken = JwtUtils.getJwtToken(jwtUserInfo);

        System.out.println(jwtToken);

        JwtUserInfo jwtUserInfoByJwtToken = getJwtUserInfoByJwtToken(jwtToken);

        System.out.println(jwtUserInfoByJwtToken);
    }

}

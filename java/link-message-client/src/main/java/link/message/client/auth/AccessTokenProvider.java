package link.message.client.auth;

/**
 * 用于管理{@link SsoAccessToken}的对象，包含获取代表应用身份的{@link SsoAccessToken}和刷新{@link SsoAccessToken}的功能
 * @author kael.
 */
public interface AccessTokenProvider {

    /**
     * 通过<code>clientId</code>和<code>clientSecret</code>获取{@link SsoAccessToken}
     * 这里clientId和clientSecret如何获取由实现类决定
     * @return 返回只代表应用身份的{@link SsoAccessToken}
     */
    SsoAccessToken obtainAccessTokenByClientCredentials();

    /**
     * 将已经过期的{@link SsoAccessToken}刷新为新的{@link SsoAccessToken}
     * @return 返回一个全新的{@link SsoAccessToken}
     */
    SsoAccessToken refreshAccessToken(SsoAccessToken accessToken);
}

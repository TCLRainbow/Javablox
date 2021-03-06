package rbxapi.javablox.client;

import okhttp3.OkHttpClient;
import rbxapi.javablox.svc.AccountInfoService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.text.MessageFormat;

public class JavabloxClient {
    private static OkHttpClient okHttpClient;
    private AccountInfoService accountInfo;

    // Non authentic client, can be used for any calls
    private static JavabloxClient instance = new JavabloxClient("");

    public static JavabloxClient getInstance() {
        return instance;
    }

    private static <T> T build(String url, Class<T> api) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MessageFormat.format("https://{0}.roblox.com", url))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(api);
    }

    /**
     * Creates a client.
     * @param cookie The cookie for authentication. If it's empty, the client isn't authenticated, but still can be used.
     */
    public JavabloxClient(String cookie) {
        okHttpClient = new OkHttpClient().newBuilder().cookieJar(new JavabloxCookieJar(cookie)).build();
        this.accountInfo = build("accountinformation", AccountInfoService.class);
    }

    public AccountInfoService accountInfo() {
        return accountInfo;
    }
}

package jlrodriguez.jlmercadolibre;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class MercadoLibreJLApplication extends MultiDexApplication {

    private static MercadoLibreJLApplication mContext;
    private String PUBLIC_KEY = "444a9ef5-8a6b-429f-abdf-587639155d88";
    private String BASE_URL = "https://api.mercadopago.com/v1/";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public String getPublicKey() {
        return PUBLIC_KEY;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public static MercadoLibreJLApplication getInstance() {
        return mContext;
    }

}

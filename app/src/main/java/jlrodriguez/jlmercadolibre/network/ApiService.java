package jlrodriguez.jlmercadolibre.network;

import java.util.List;

import io.reactivex.Observable;
import jlrodriguez.jlmercadolibre.model.CardIssuer;
import jlrodriguez.jlmercadolibre.model.CreditCard;
import jlrodriguez.jlmercadolibre.model.InstallmentObject;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String PAYMENT_METHODS  = "payment_methods";
    String CARD_ISSUERS = PAYMENT_METHODS + "/card_issuers";
    String PUBLIC_KEY = "public_key";
    String PAYMENT_METHOD_ID = "payment_method_id";
    String INSTALLMENTS = PAYMENT_METHODS + "/installments";
    String AMOUNT = "amount";
    String ISSUER_ID = "issuer.id";

    @GET(PAYMENT_METHODS)
    Observable<List<CreditCard>> getPaymentMethods(@Query(PUBLIC_KEY) String publicKey);

    @GET(CARD_ISSUERS)
    Observable<List<CardIssuer>> getBankByPaymentMethod(@Query(PUBLIC_KEY) String publicKey,
                                                        @Query(PAYMENT_METHOD_ID) String paymentMethodId);

    @GET(INSTALLMENTS)
    Observable<List<InstallmentObject>> getInstallmentsByBank(@Query(PUBLIC_KEY) String publicKey,
                                                              @Query(PAYMENT_METHOD_ID) String paymentMethodId,
                                                              @Query(AMOUNT) String amount,
                                                              @Query(ISSUER_ID) String issuerId);
}
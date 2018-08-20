package jlrodriguez.jlmercadolibre.activity;

import java.util.List;

import jlrodriguez.jlmercadolibre.model.CreditCard;

public interface SelectPaymentMethodViewInterface {
    void displayCreditCards(List<CreditCard> movieResponse);
    void displayError();
}

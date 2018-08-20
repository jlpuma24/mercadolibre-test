package jlrodriguez.jlmercadolibre.activity;

import java.util.List;

import jlrodriguez.jlmercadolibre.model.CardIssuer;

public interface SelectBankViewInterface {
    void displayBanks(List<CardIssuer> banks);
    void displayError();
}

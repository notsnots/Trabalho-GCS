package edu.pucrs.br.trade;

import java.util.ArrayList;

public class Trades {
    public ArrayList<TradeEntity> trades = new ArrayList<>();

    /*
        Este método é responsável por criar uma nova proposta de troca.
        Através de outros métodos, é possível alterar o status da proposta.
    */
    public void createTrade(TradeEntity trade) {
        this.trades.add(trade);
    }
}

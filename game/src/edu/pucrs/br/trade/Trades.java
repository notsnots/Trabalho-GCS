package edu.pucrs.br.trade;

import java.util.ArrayList;
import java.util.stream.Collectors;

import edu.pucrs.br.player.PlayerEntity;

public class Trades {
    public ArrayList<TradeEntity> trades = new ArrayList<>();

    /*
        Este método é responsável por criar uma nova proposta de troca.
        Através de outros métodos, é possível alterar o status da proposta.
    */
    public void createTrade(TradeEntity trade) {
        this.trades.add(trade);
    }

    public ArrayList<TradeEntity> getPendingTrades(PlayerEntity p){
        return trades
                .stream()
                .filter(trade ->
                        (trade.getTargetPlayer().getEmail().equals(p.getEmail()) || trade.getSourcePlayer().getEmail().equals(p.getEmail()))
                                && trade.isPending())
        .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getTotalTrades(){
        return trades.size();
    }

    public int getTotalPendingTrades(){
        return (int) trades
                .stream()
                .filter(TradeEntity::isPending)
                .count();
    }

    public int getTotalAcceptedTrades(){
        return (int) trades
                .stream()
                .filter(TradeEntity::isAccepted)
                .count();
    }

    public int getTotalDeniedTrades(){
        return (int) trades
                .stream()
                .filter(TradeEntity::isDenied)
                .count();
    }
}

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

<<<<<<< HEAD
    public ArrayList<TradeEntity> getPendingTrades(PlayerEntity p){
        return trades
                .stream()
                .filter(trade ->
                        (trade.getTargetPlayer().getEmail().equals(p.getEmail()) || trade.getSourcePlayer().getEmail().equals(p.getEmail()))
                                && !trade.isAccepted())
        .collect(Collectors.toCollection(ArrayList::new));
    }
}
=======
    public ArrayList<TradeEntity> getPendingTradesForPlayer(PlayerEntity p){
         return trades.stream()
        .filter(trade -> trade.getTargetPlayer().getEmail().equals(p.getEmail()) && !trade.isAccepted())
        .collect(Collectors.toCollection(ArrayList::new));
    }
}
>>>>>>> 058352aeb9648acc6a886febb9209c7d12d9af33

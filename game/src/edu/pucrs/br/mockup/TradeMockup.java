package edu.pucrs.br.mockup;

import edu.pucrs.br.item.ItemEntity;
import edu.pucrs.br.player.PlayerEntity;
import edu.pucrs.br.player.Players;
import edu.pucrs.br.trade.TradeEntity;
import edu.pucrs.br.trade.Trades;

public class TradeMockup {
    public static void insert(Players players, Trades trades) {
        PlayerEntity playerOne = players.getByEmail("player0@gmail.com");
        PlayerEntity playerTwo = players.getByEmail("player1@gmail.com");

        ItemEntity playerOneTradeItem = playerOne.getItems().get(0);
        ItemEntity playerTwoTradeItem = playerTwo.getItems().get(0);

        TradeEntity trade = new TradeEntity(playerOne, playerTwo, playerOneTradeItem.getId(), playerTwoTradeItem.getId());
        trades.createTrade(trade);

        System.out.println("[TradeMockup::insert] Mockup inserido com sucesso!");
    }
}

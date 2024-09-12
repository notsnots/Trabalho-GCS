package edu.pucrs.br.trade;

import edu.pucrs.br.player.PlayerEntity;

import java.util.UUID;

public class TradeEntity {
    private PlayerEntity source;
    private PlayerEntity target;
    private UUID sourceItemId;
    private UUID targetItemId;
    private TradeStatus status;

    public TradeEntity(PlayerEntity source, PlayerEntity target, UUID sourceItemId, UUID targetItemId) {
        this.source = source;
        this.target = target;

        try {
            source.hasItem(sourceItemId);
            target.hasItem(targetItemId);
        } catch (Error e) {
            System.out.println("Erro ao criar a proposta de troca: " + e.getMessage());
        }

        this.sourceItemId = sourceItemId;
        this.targetItemId = targetItemId;
        this.status = TradeStatus.PENDING;
    }
}

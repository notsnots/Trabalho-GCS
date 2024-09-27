package edu.pucrs.br.trade;

import edu.pucrs.br.item.ItemEntity;
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

    public PlayerEntity getTargetPlayer() {
        return this.target;
    }

    public boolean isAccepted() {
        if (this.status == TradeStatus.ACCEPTED) {
            return true;
        }
        return false;
    }

    public PlayerEntity getSourcePlayer() {
        return this.source;
    }

    public UUID getSourceItem() {
        return this.sourceItemId;
    }

    public UUID getTargetItem() {
        return this.targetItemId;
    }



}

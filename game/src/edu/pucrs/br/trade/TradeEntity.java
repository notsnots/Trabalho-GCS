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

    public PlayerEntity getTargetPlayer(){
        return this.target;
    }

    public PlayerEntity getSourcePlayer(){
        return this.source;
    }

    public UUID getTargetItem(){
        return this.targetItemId;
    }

    public UUID getSourceItem(){
        return this.sourceItemId;
    }

    public boolean isPending(){
        return this.status == TradeStatus.PENDING;
    }

    public boolean isAccepted(){
        return this.status == TradeStatus.ACCEPTED;
    }

    public boolean isDenied(){
        return this.status == TradeStatus.DENIED;
    }




    public void setStatus(TradeStatus status){
        this.status = status;
    }
}

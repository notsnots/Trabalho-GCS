import edu.pucrs.br.mockup.PlayerMockup;
import edu.pucrs.br.mockup.TradeMockup;
import edu.pucrs.br.player.Players;
import edu.pucrs.br.trade.TradeStatus;
import edu.pucrs.br.trade.Trades;
import edu.pucrs.br.ui.UI;

public class App {
    private final Players players = new Players();
    private final Trades trades = new Trades();

    /*
        Este método é o entry-point da aplicação.
    */
    public void bootstrap() {
        this.mockupInsert();

        new UI(this.players, this.trades);
    }

    /*
        Este método é responsável por gerênciar os mockups inseridos visando
        facilitar os testes durante o desenvolvimento do sistema.
    */
    public void mockupInsert() {
        PlayerMockup.insert(this.players);
        TradeMockup.insert(this.players, this.trades);
    }
}

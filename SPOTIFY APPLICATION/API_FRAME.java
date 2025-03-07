import javax.swing.JFrame;

public class API_FRAME extends JFrame {
    API_FRAME()    {
        setTitle("SPOTIFY");
        setSize(500,500);
        setLocationRelativeTo(null);
        API_PANEL apiPanel = new API_PANEL();
        add(apiPanel);
        setVisible(true);
    }
}

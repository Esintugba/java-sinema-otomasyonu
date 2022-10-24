package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void DegisiklikButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	public static void showMsg(String str) {
		
		String msg;
		DegisiklikButtonText();
		
		switch(str) {
		case "fill":
			msg="L�tfen Bo� Alanlar� Doldurunuz!";
			break;
		case "success":
			msg="��lem Ba�ar�l�";
			break;
		case "error":
			msg="Hatal� Giri� Tekrar Deneyin!";
			break;
		
		default:
			msg=str;
			
		}
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}

    public static boolean confirm(String str) {
    	
    	String msg;
    	DegisiklikButtonText();
    	switch(str) {
    	case "sure":
    		msg="Bu i�lemi ger�ekle�tirmek istiyor musunuz?";
    		break;
    	default:
    		msg=str;
    		break;
    	}
    	int sonuc = JOptionPane.showConfirmDialog(null, msg,"Dikkat!",JOptionPane.YES_NO_OPTION);
    	if (sonuc==0) {
    		return true;
    	}
    	else {
    		return false;
    	}
	
  }


}

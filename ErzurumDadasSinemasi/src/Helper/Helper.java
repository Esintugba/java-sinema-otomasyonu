package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void DegisiklikButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
	}
	public static void showMsg(String str) {
		
		String msg;
		DegisiklikButtonText();
		
		switch(str) {
		case "fill":
			msg="Lütfen Boþ Alanlarý Doldurunuz!";
			break;
		case "success":
			msg="Ýþlem Baþarýlý";
			break;
		case "error":
			msg="Hatalý Giriþ Tekrar Deneyin!";
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
    		msg="Bu iþlemi gerçekleþtirmek istiyor musunuz?";
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

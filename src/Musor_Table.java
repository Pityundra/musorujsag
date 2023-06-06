import java.util.List;
import javax.swing.table.AbstractTableModel;


class Musor_Table extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int CÍME_COL = 0;
	private static final int FORGATÁSI_ORSZÁG_COL = 1;
	private static final int KIADÁS_ÉVE_COL = 2;
	private static final int KORHATÁR_COL = 3;
	private static final int STÍLUS_COL = 4;
	private static final int IDŐ_TARTAM_COL = 5;

	private String[] oszlopNevek = { "címe", "forgatási_ország", "kiadás_éve",
			"korhatár", "stílus", "idő_tartam" };
	private List<Musor> musorok;

	public Musor_Table(List<Musor> musorok) {
		this.musorok = musorok;
	} //ez a this lehet baj

	@Override
	public int getColumnCount() {
		return oszlopNevek.length;
	}

	@Override
	public int getRowCount() {
		return musorok.size();
	}

	@Override
	public String getColumnName(int col) {
		return oszlopNevek[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Musor musor = musorok.get(row);

		switch (col) {
		case CÍME_COL:
			return musor.getCime();
		case FORGATÁSI_ORSZÁG_COL:
			return musor.getForgatasi_orszag();
		case KIADÁS_ÉVE_COL:
			return musor.getKiadas_eve();
		case KORHATÁR_COL:
			return musor.getKorhatar();
			case STÍLUS_COL:
				return musor.getStilus();
			case IDŐ_TARTAM_COL:
				return musor.getIdo_tartam();
			case OBJECT_COL:
				return musor;
		default:
			return musor.getCime();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

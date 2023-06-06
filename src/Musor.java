public class Musor {

    private String cime;
    private String forgatasi_orszag;
    private String kiadas_eve;
    private int korhatar;
    private String stilus;
    private int ido_tartam;


    public Musor(String cime, String forgatasi_orszag, String kiadas_eve, int korhatar, String stilus, int ido_tartam) {
        this.cime = cime;
        this.forgatasi_orszag = forgatasi_orszag;
        this.kiadas_eve = kiadas_eve;
        this.korhatar = korhatar;
        this.stilus = stilus;
        this.ido_tartam = ido_tartam;
    }

    public String getCime() {
        return cime;
    }

    public void setCime(String cime) {
        this.cime = cime;
    }

    public String getForgatasi_orszag() {
        return forgatasi_orszag;
    }

    public void setForgatasi_orszag(String forgatasi_orszag) {
        this.forgatasi_orszag = forgatasi_orszag;
    }

    public String getKiadas_eve() {
        return kiadas_eve;
    }

    public void setKiadas_eve(String kiadas_eve) {
        this.kiadas_eve = kiadas_eve;
    }

    public int getKorhatar() {
        return korhatar;
    }

    public void setKorhatar(int korhatar) {
        this.korhatar = korhatar;
    }

    public String getStilus() {
        return stilus;
    }

    public void setStilus(String stilus) {
        this.stilus = stilus;
    }

    public int getIdo_tartam() {
        return ido_tartam;
    }

    public void setIdo_tartam(int ido_tartam) {
        this.ido_tartam = ido_tartam;
    }

    @Override
    public String toString() {
        return "Musor{" +
                "cime='" + cime + '\'' +
                ", forgatasi_orszag='" + forgatasi_orszag + '\'' +
                ", kiadas_eve='" + kiadas_eve + '\'' +
                ", korhatar=" + korhatar +
                ", stilus='" + stilus + '\'' +
                ", ido_tartam=" + ido_tartam +
                '}';
    }
}

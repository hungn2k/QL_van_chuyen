package OOP;
import javafx.scene.control.CheckBox;
import java.time.LocalDate;
abstract class Donhang {
    protected String nguoinhan;
    protected String nguoigui;
    protected String diachi;
    protected double khoangcach;
    protected double cannang;
    protected CheckBox chon;
    protected LocalDate ngay;
    public Donhang(String nguoigui,String nguoinhan,String diachinhan,double khoangcach,double cannang,LocalDate ngay)
    {
        this.nguoigui=nguoigui;
        this.nguoinhan=nguoinhan;
        this.diachi=diachinhan;
        this.khoangcach=khoangcach;
        this.cannang=cannang;
        this.chon=new CheckBox();
        this.ngay=ngay;
    }
    public String getNguoinhan()
    {
        return nguoinhan;
    }
    public void setNguoinhan(String nguoinhan)
    {
        this.nguoinhan=nguoinhan;
    }
    public String getNguoigui()
    {
        return nguoigui;
    }
    public void setNguoigui(String nguoigui)
    {
        this.nguoigui=nguoigui;
    }
    public String getDiachi()
    {
        return diachi;
    }
    public void setDiachi(String diachinhan)
    {
        this.diachi=diachinhan;
    }
    public double getKhoangcach() { return khoangcach; }
    public void setKhoangcach(double khoangcach)
    {
        this.khoangcach=khoangcach;
    }
    public double getCannang()
    {
        return cannang;
    }
    public void setCannang(double cannang)
    {
        this.cannang=cannang;
    }
    public CheckBox getChon() { return chon;}
    public LocalDate getNgay() { return ngay; }
    public void setNgay(LocalDate ngay) { this.ngay=ngay;}
    abstract public double chiphi();
    abstract public String loaihang();
}

package OOP;
import java.time.LocalDate;
public class Hangkhong extends Donhang {
    private final int phidv = 200000;
    public Hangkhong(String nguoigui, String nguoinhan, String diachinhan, double khoangcach, double cannang, LocalDate ngay) {
        super(nguoigui, nguoinhan, diachinhan, khoangcach, cannang, ngay);
    }
    public double chiphi() {
        return khoangcach * 100000 + cannang * 10000 + phidv;
    }
    public String loaihang(){
        return "Hàng Không";
    }
    public String toString()
    {
        return "Tên người gửi: "+nguoigui+"\n"+
                "Tên người nhận: "+nguoinhan+"\n"+
                "Địa chỉ nhận: "+diachi+"\n"+
                "Khoảng cách: "+khoangcach+"\n"+
                "Chi phí: "+chiphi()+"\n"+
                "Loại hàng: "+loaihang()+"\n"+
                "Ngày giao hàng: "+ngay;
    }
}
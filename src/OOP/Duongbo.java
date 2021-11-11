package OOP;
import java.time.LocalDate;
public class Duongbo extends Donhang {
    public Duongbo(String nguoigui, String nguoinhan, String diachinhan, double khoangcach, double cannang, LocalDate ngay) {
        super(nguoigui, nguoinhan, diachinhan, khoangcach, cannang,ngay);
    }

    public double chiphi() {
        return khoangcach * 20000 + cannang * 5000;
    }
    public String loaihang(){
        return "Đường Bộ";
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
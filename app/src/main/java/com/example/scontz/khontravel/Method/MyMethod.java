package com.example.scontz.khontravel.Method;

/**
 * Created by scOnTz on 23/10/2559.
 */
public class MyMethod {
    String[] listPname = {"ชายทะเลปากพนังและแหลมตะลุมพุก", "หาดคอเขา", "หาดในเพลา", "หาดสระบัว",
            "หาดสิชล", "หาดหินงาม", "อ่าวขนอม", "อ่าวท้องหยี", "น้ำตกกรุงชิง", "น้ำตกกะโรม", "น้ำตกคลองจัง",
            "น้ำตกท่าแพ", "น้ำตกปลายอวน", "น้ำตกปลิว", "น้ำตกพรหมโลก", "น้ำตกยอดเหลือง", "น้ำตกสวนขัน",
            "น้ำตกสวนอาย", "น้ำตกสี่ขีด", "น้ำตกอ้ายเขียว", "อุทยานแห่งชาติน้ำตกโยง", "เขาพลายดำ", "เขาเหมน",
            "หมู่บ้านคิรีวง", "อุทยานแห่งชาติเขานัน", "อุทยานแห่งชาติเขาหลวง", "ล่องแก่งคลองกลาย", "ถ้ำแก้วสุรกานต์",
            "ถ้ำเขาวังทอง", "ถ้ำตลอด", "ถ้ำหงส์"};
    String[] listCode = {"B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08", "W01", "W02", "W03",
            "W04", "W05", "W06", "W07", "W08", "W09", "W10", "W11", "W12", "W13", "M01", "M02", "M03",
            "M04", "M05", "K01", "C01", "C02", "C03", "C04"};

    String[] listPtype = {"ทะเล", "น้ำตก", "ภูเขา", "แก่ง", "ถ้ำ"};
    String[] listPcode = {"B", "W", "M", "K", "C"};

    public String changePnameToCode(String pname) {
        String pnCode = "";
        for (int i = 0; i < listPname.length; i++) {
            if (pname.equals(listPname[i])) {
                pnCode = listCode[i];
            }
        }
        return pnCode;
    }//เปลี่ยนชื่อสถานที่เป็นโค๊ด

    public String changePtypeToCode(String ptype) {
        String ptypeCode = "";
        for (int i = 0; i < listPtype.length; i++) {
            if (ptype.equals(listPtype[i])) {
                ptypeCode = listPcode[i];
            }
        }
        return ptypeCode;
    }//เปลี่ยนชื่อประเภทเป็นโค๊ด

}// Main Class

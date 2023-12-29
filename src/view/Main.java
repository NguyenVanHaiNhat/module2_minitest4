package view;

import manager.MaterialManager;
import model.CrispyFlour;
import model.Material;
import model.Meat;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

import static manager.MaterialManager.readDataFromFile;
import static manager.MaterialManager.writeDataToFile;


public class Main {

    public static void main(String[] args) {
        CrispyFlour crispyFlour1 = new CrispyFlour("001", "Ajinomoto", LocalDate.of(2016, Month.AUGUST, 1), 36000, 12);
        CrispyFlour crispyFlour2 = new CrispyFlour("002", "CJ", LocalDate.of(2018, Month.JANUARY, 15), 31000, 21);
        CrispyFlour crispyFlour3 = new CrispyFlour("003", "Meizan", LocalDate.of(2022, Month.MARCH, 16), 25000, 15);
        CrispyFlour crispyFlour4 = new CrispyFlour("004", "Tài Ký", LocalDate.of(2023, Month.OCTOBER, 22), 55000, 9);
        CrispyFlour crispyFlour5 = new CrispyFlour("005", "CJ Food", LocalDate.of(2019, Month.DECEMBER, 20), 355000, 8);

        Meat meat1 = new Meat("006", "chicken", LocalDate.of(2018, Month.JANUARY, 8), 25000, 45);
        Meat meat2 = new Meat("007", "cow", LocalDate.of(2016, Month.JULY, 6), 23000, 100);
        Meat meat3 = new Meat("008", "duck", LocalDate.of(2022, Month.OCTOBER, 22), 10000, 35);
        Meat meat4 = new Meat("009", "dog", LocalDate.of(2023, Month.DECEMBER, 12), 20000, 62);
        Meat meat5 = new Meat("010", "pig", LocalDate.of(2023, Month.NOVEMBER, 23), 13000, 87);


        MaterialManager materialManager = new MaterialManager();
        materialManager.addMaterial(crispyFlour1);
        materialManager.addMaterial(crispyFlour2);
        materialManager.addMaterial(crispyFlour3);
        materialManager.addMaterial(crispyFlour4);
        materialManager.addMaterial(crispyFlour5);
        materialManager.addMaterial(meat1);
        materialManager.addMaterial(meat2);
        materialManager.addMaterial(meat3);
        materialManager.addMaterial(meat4);
        materialManager.addMaterial(meat5);
        double totalCost = materialManager.calculateTotalCost();

        int choice = -1;
        Scanner input = new Scanner(System.in);

        while (choice != 0){
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Tổng tiền của 10 vật liệu");
            System.out.println("2. Sắp xếp vật liệu theo giá");
            System.out.println("3. Tính số chênh lệch giữa chiết khấu và không chiết khấu tại ngày hôm nay");
            System.out.println("4. thêm đối tượng");
            System.out.println("5. sửa đối tượng");
            System.out.println("6. xóa đối tượng");
            System.out.println("7. xuất danh sách ra file");
            System.out.println("8. Đọc danh sách từ file");
            System.out.println("9. thoát");
            System.out.println("--------------------------------------------------------------");
            System.out.print("nhập lựa chọn của bạn : ");
            choice = input.nextInt();
            
            switch (choice){
                case 1:
                    System.out.println("\n" +
                        "tổng tiền của 10 vật liệu là : " + totalCost + "VNĐ");
                    System.out.println();
                    break;
                case 2:
                    materialManager.sortMaterialByCost();
                        System.out.println("Sắp xếp vật liệu theo giá:");
                        System.out.println();
                        for (Material material : materialManager.materials) {
                            System.out.println(material.getName() + " - Cost: " + material.getAmount() + "VNĐ");
                        }
                        System.out.println();
                        break;
                case 3:
                    double discountToday = materialManager.calculateDiscountToday();
                    double totalCostWithDiscount = totalCost - discountToday;
                    System.out.println("chênh lệch giữa chiết khấu và không chiết khấu tại ngày hôm nay : " + totalCostWithDiscount + "VNĐ");
                    break;
                case 4:
                    Meat meat6 = new Meat("010", "pig", LocalDate.of(2023, Month.NOVEMBER, 23), 13000, 87);
                    materialManager.addMaterial(meat6);
                    break;
                case 5:
                    Material meat7 = new Meat("011", "aaa", LocalDate.of(2023, Month.AUGUST, 25), 15000, 55);
                    materialManager.updateMaterial(meat7);
                    break;
                case 7:
                    writeDataToFile("abc.txt", materialManager.materials);
                    break;
                case 8:
                    List<Material> loadedMaterials = readDataFromFile("abc.txt");
                    System.out.println(loadedMaterials);
                    break;
                case 9:
                    System.exit(0);
            }
        }

    }




}
package manager;

import model.CrispyFlour;
import model.Material;
import model.Meat;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaterialManager implements Serializable {
    public List<Material> materials;

    public MaterialManager(){
        this.materials = new ArrayList<>();
    }

    public void addMaterial(Material material){
        materials.add(material);
    }

    public void updateMaterial(Material material) {
        for (int i = 0; i < materials.size(); i++) {
            Material existingMaterial = materials.get(i);

            if (existingMaterial.getId().equals(material.getId())) {
                materials.set(i, material);
                System.out.println("Vật liệu được cập nhật thành công");
            }
            else {
                System.out.println("Không tồn tại vật liệu để sửa");
            }
        }
    }

    public void deleteMaterial(Material material){
        materials.remove(material);
    }

    public void sortMaterialByCost(){
        materials.sort(Comparator.comparingDouble(Material::getAmount));
    }
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Material material : materials) {
            totalCost += material.getAmount();
        }
        return totalCost;
    }

    public double calculateDiscountToday() {
        double totalDiscount = 0;
        LocalDate today = LocalDate.now();

        for (Material material : materials) {
            double discountRate = 0;

            if (material instanceof Meat) {
                LocalDate expiryDate = material.getExpiryDate();
                if (expiryDate.minusDays(5).isBefore(today)) {
                    discountRate = 0.3;
                }
            } else if (material instanceof CrispyFlour) {
                LocalDate expiryDate = material.getExpiryDate();
                LocalDate twoMonthsBefore = today.plusMonths(2);
                LocalDate fourMonthsBefore = today.plusMonths(4);

                if (expiryDate.isBefore(twoMonthsBefore)) {
                    discountRate = 0.4;
                } else if (expiryDate.isBefore(fourMonthsBefore)) {
                    discountRate = 0.2;
                } else {
                    discountRate = 0.05;
                }
            }

            totalDiscount += material.getAmount() * discountRate;
        }

        return totalDiscount;
    }
    public static void writeDataToFile(String source, List<Material> materials) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(source))) {
            oos.writeObject(materials);
            System.out.println("Data written to file successfully.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static List<Material> readDataFromFile(String source) {
        List<Material> materials = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(source))) {
            materials = (List<Material>) ois.readObject();
            System.out.println("Data read from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return materials;
    }


}


package eightman.library.GUI.MODULE;

public class Equipment_Module {
    private String id;
    private String content;
    private String category;
    private double lend_lease_cost;
    private double build_cost_ic;
    private int manpower;
    private boolean can_license;
    private boolean is_convertable;
    private double xp_cost;

    public Equipment_Module(String id, String content, String category, double lend_lease_cost, double build_cost_ic, int manpower, boolean can_license, boolean is_convertable, double xp_cost) {
        this.id = id;
        this.content = content;
        this.category = category;
        this.lend_lease_cost = lend_lease_cost;
        this.build_cost_ic = build_cost_ic;
        this.manpower = manpower;
        this.can_license = can_license;
        this.is_convertable = is_convertable;
        this.xp_cost = 0;

    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public double getLendLeaseCost() {
        return lend_lease_cost;
    }

    public double getBuildCostIc() {
        return build_cost_ic;
    }

    public int getManpower() {
        return manpower;
    }

    public boolean isCanLicense() {
        return can_license;
    }

    public boolean isIsConvertable() {
        return is_convertable;
    }
    public double getXpCost() {
        return xp_cost;
    }
}

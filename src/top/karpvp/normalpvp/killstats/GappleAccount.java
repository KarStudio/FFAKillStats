package top.karpvp.normalpvp.killstats;

public class GappleAccount {
    private String account;

  public GappleAccount(String name)
  {
    this.account = name.toLowerCase();
  }

  public String getAccount() {
    return this.account;
  }

  public int getGappleKill() {
    return Main.getGappleAPI().getGappleKill(this.account);
  }

  public boolean setGappleKill(int amount,String pn) {
    return Main.getGappleAPI().setGappleKill(this.account, amount,pn);
  }

  public boolean GappleKillEnough(int amount) {
    return getGappleKill() >= amount;
  }

  public boolean addCredit(int amount,String pn) {
    return setGappleKill(getGappleKill() + amount,pn);
  }

  public boolean removeGappleKill(int amount,String pn) {
    return setGappleKill(getGappleKill() - amount,pn);
  }
}

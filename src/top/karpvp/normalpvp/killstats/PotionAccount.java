package top.karpvp.normalpvp.killstats;

public class PotionAccount
{
  private String account;

  public PotionAccount(String name)
  {
    this.account = name.toLowerCase();
  }

  public String getAccount() {
    return this.account;
  }

  public int getKarma() {
    return Main.getPotionAPI().getPotionKill(this.account);
  }

  public boolean setKarma(int karma,String pn) {
    return Main.getPotionAPI().setPotionKill(this.account, karma,pn);
  }

  public boolean karmaEnough(int karma) {
    return getKarma() >= karma;
  }

  public boolean addKarma(int karma,String pn) {
    return setKarma(getKarma() + karma,pn);
  }

  public boolean takeKarma(int karma,String pn) {
    return setKarma(getKarma() - karma,pn);
  }
}
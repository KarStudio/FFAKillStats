package top.karpvp.normalpvp.killstats;

public class Account
{
  private String account;

  public Account(String name)
  {
    this.account = name.toLowerCase();
  }

  public String getAccount() {
    return this.account;
  }

  public int getCoins() {
    return Main.getFFAAPI().getKill(this.account);
  }

//  public boolean setCoins(int coins) {
//    return Main.getFFAAPI().setKill(getPlayer(this.account), coins);
//  }

  public boolean coinsEnough(int coins) {
    return getCoins() >= coins;
  }

//  public boolean addCoins(int coins) {
//    return setCoins(getCoins() + coins);
//  }

//  public boolean takeCoins(int coins) {
//    return setCoins(getCoins() - coins);
//  }
}
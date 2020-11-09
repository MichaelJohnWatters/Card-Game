public enum Rank {
    KING("KING"),
    QUEEN("QUEEN"),
    JACK("JACK"),
    PLAIN("PLAIN");

    private String rankValue;

    Rank(String rankValue) {
        this.rankValue = rankValue;
    }

    public String getRankValue() {
        return rankValue;
    }


}

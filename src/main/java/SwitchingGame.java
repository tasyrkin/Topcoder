public class SwitchingGame {

    public int timeToWin(final String[] states) {
        int N = states.length;
        int M = states[0].length();
        char[] state = new char[states[0].length()];
        for (int i = 0; i < states[0].length(); i++) {
            state[i] = '-';
        }

        int count = 0;
        for (int lvl = 0; lvl < N; lvl++) {

            for (boolean minPlus : new Boolean[] {false, true}) {
                boolean needSwitch = false;
                for (int i = 0; i < M; i++) {
                    if (states[lvl].charAt(i) != '?') {
                        if (minPlus) {
                            if (states[lvl].charAt(i) == '+' && states[lvl].charAt(i) != state[i]) {
                                needSwitch = true;
                                break;
                            }
                        } else {
                            if (states[lvl].charAt(i) == '-' && states[lvl].charAt(i) != state[i]) {
                                needSwitch = true;
                                break;
                            }
                        }
                    }
                }

                if (needSwitch) {
                    for (int i = 0; i < M; i++) {
                        if (states[lvl].charAt(i) != '?') {
                            if (minPlus) {
                                if (states[lvl].charAt(i) == '+' && states[lvl].charAt(i) != state[i]) {
                                    state[i] = states[lvl].charAt(i);
                                }
                            } else {
                                if (states[lvl].charAt(i) == '-' && states[lvl].charAt(i) != state[i]) {
                                    state[i] = states[lvl].charAt(i);
                                }
                            }
                        } else {
                            for (int currLvl = lvl + 1; currLvl < N; currLvl++) {
                                if (minPlus) {
                                    if (states[currLvl].charAt(i) != '?' && states[currLvl].charAt(i) == '+'
                                            && states[currLvl].charAt(i) != state[i]) {
                                        state[i] = states[currLvl].charAt(i);
                                        break;
                                    }
                                } else {
                                    if (states[currLvl].charAt(i) != '?' && states[currLvl].charAt(i) == '-'
                                            && states[currLvl].charAt(i) != state[i]) {
                                        state[i] = states[currLvl].charAt(i);
                                        break;
                                    }

                                }
                            }
                        }
                    }

                    count++;
                } else {
                    boolean changed = false;
                    for (int i = 0; i < M; i++) {
                        if (states[lvl].charAt(i) != '?') {
                            if (minPlus) {
                                if (states[lvl].charAt(i) == '+' && states[lvl].charAt(i) != state[i]) {
                                    state[i] = states[lvl].charAt(i);
                                    changed = true;
                                }
                            } else {
                                if (states[lvl].charAt(i) == '-' && states[lvl].charAt(i) != state[i]) {
                                    state[i] = states[lvl].charAt(i);
                                    changed = true;
                                }

                            }
                        }
                    }

                    count += changed ? 1 : 0;

                }

            }

            count++;

        }

        return count;
    }

}

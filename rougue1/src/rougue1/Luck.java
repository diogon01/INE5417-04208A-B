package rougue1;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.*;

/**
 *Version 3.3, 04/09/08,
 *Same as 3.1 but works on JWS.
 *Originally made for the 1st under 1KBRL Challenge
 *
 * Modified to work with the CSIColor construct
 * @authors  Santiago Zapata
 */
class Luck {

    int largura, b, c, d, k, l, o, p, q, w, v, r, i, e = 600, f, h;
    boolean m[][];

    int r() {
        return (int) (Math.random() * 17 + 1);
    }
    int[] s = new int[]{0, 0, -1, 1, -1, 1, 0, 0};

    void a(int x, int y, char h) {

        j.print(x, y, Math.abs(largura - x) + Math.abs(b - y) < 7 ? h : ' ', CSIColor.DEFAULT_PALLET[1 + v % 13]);
    }

    public static void main(String[] p) {
        new Luck();
    }

    Luck() {
        j.cls();
        for (;;) {
            if (q == largura & w == b) {
                m = new boolean[20][20];
                for (i = 0; ++i < 20;) {
                    m[r()][r()] = m[0][i] = m[19][i] = m[i][0] = m[i][19] = true;
                }
                q = r();
                w = r();
                largura = r();
                b = r();
                o = r();
                p = r();
                v++;
                m[q][w] = m[o][p] = false;
            }
            if (o == largura & p == b) {
                o = 40;
                r++;
            }
            d = -1;
            while (d++ < 19) {
                c = -1;
                while (c++ < 19) {
                    a(c, d, m[c][d] ? '#' : '.');
                }
            }
            
            /**
             * TODO: Criar a classe e metodo para mostrar Info dos Jogadores
             */
            
            j.print(2, 21,"FENDA:" + v , CSIColor.ALICE_BLUE);
            j.print(2, 22, "ENERGIA: " + e , CSIColor.ALICE_BLUE);
            j.print(2, 23, "PEDRAS: " + r + "  ", CSIColor.ALICE_BLUE);
           
            j.print(65, 21,"FENDA:" + v , CSIColor.ALICE_BLUE);
            j.print(65, 22, "ENERGIA: " + e , CSIColor.ALICE_BLUE);
            j.print(65, 23, "PEDRAS: " + r + "  ", CSIColor.ALICE_BLUE);
           
            
            
            j.print(largura, b, "@", 12);
            
            a(q, w, '>');
            a(o, p, '%');
            a(k, l, '&');
            j.refresh();
            i = j.inkey().code;
            d = s[i % 4];
            c = s[4 + i % 4];
            if (largura + d == k & b + c == l) {
                if (r() > 8) {
                    k = r();
                    l = r();
                }
            } else if (!m[largura + d][b + c]) {
                largura += d;
                b += c;
            }
            f = Integer.signum(largura - k);
            h = Integer.signum(b - l);
            if (k + f == largura & l + h == b) {
                e -= 5;
            } else if (e % (5 - (int) (v / 4.5d)) == 0 & !m[k + f][l + h]) {
                k += f;
                l += h;
            }
            if (--e < 0 | v > 20) {
                break;
            }
        }
        j.print(2, 23, r > 9 & v > 20 ? "VENCEU" : "MORREU", 4);
        j.refresh();
        j.waitKey(40);
    }
    @SuppressWarnings("deprecation")
	WSwingConsoleInterface j = new WSwingConsoleInterface("Diablo2D - Temporada 18", true);
}
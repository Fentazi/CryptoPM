

import java.util.Arrays;

class Kuznechik{

    static byte[] int_to_byte(int[] res) {
        byte[] ut = new byte[res.length];
        for (int i = 0; i < res.length; i++) {
            ut[i] = (byte) res[i];
        }
        return ut;
    }
    
    private String lengthTo32Bytes(String str) {
        Stribog hash = new Stribog();
        byte[] res = hash.getHash(str.getBytes());
        for (int i = 0; i < 10000; i++)
            res = hash.getHash(res);
        String out = "";
        for (byte b : res) {
            out += Integer.toHexString(b);
        }
        return out;
    }

    private byte[][] iterC = new byte[32][];
    private byte[][] iterK = new byte[10][];
    
    private final byte[] Pi = int_to_byte(new int[]{
        252, 238, 221, 17, 207, 110, 49, 22, 251, 196, 250, 218, 35, 197, 4, 77,
        233, 119, 240, 219, 147, 46, 153, 186, 23, 54, 241, 187, 20, 205, 95, 193,
        249, 24, 101, 90, 226, 92, 239, 33, 129, 28, 60, 66, 139, 1, 142, 79,
        5, 132, 2, 174, 227, 106, 143, 160, 6, 11, 237, 152, 127, 212, 211, 31,
        235, 52, 44, 81, 234, 200, 72, 171, 242, 42, 104, 162, 253, 58, 206, 204,
        181, 112, 14, 86, 8, 12, 118, 18, 191, 114, 19, 71, 156, 183, 93, 135,
        21, 161, 150, 41, 16, 123, 154, 199, 243, 145, 120, 111, 157, 158, 178, 177,
        50, 117, 25, 61, 255, 53, 138, 126, 109, 84, 198, 128, 195, 189, 13, 87,
        223, 245, 36, 169, 62, 168, 67, 201, 215, 121, 214, 246, 124, 34, 185, 3,
        224, 15, 236, 222, 122, 148, 176, 188, 220, 232, 40, 80, 78, 51, 10, 74,
        167, 151, 96, 115, 30, 0, 98, 68, 26, 184, 56, 130, 100, 159, 38, 65,
        173, 69, 70, 146, 39, 94, 85, 47, 140, 163, 165, 125, 105, 213, 149, 59,
        7, 88, 179, 64, 134, 172, 29, 247, 48, 55, 107, 228, 136, 217, 231, 137,
        225, 27, 131, 73, 76, 63, 248, 254, 141, 83, 170, 144, 202, 216, 133, 97,
        32, 113, 103, 164, 45, 43, 9, 91, 203, 155, 37, 208, 190, 229, 108, 82,
        89, 166, 116, 210, 230, 244, 180, 192, 209, 102, 175, 194, 57, 75, 99, 182
    });

    private final byte[] Pi_Reverse = int_to_byte(new int[]{
      165, 45, 50, 143, 14, 48, 56, 192, 84, 230, 158, 57, 85, 126, 82, 145,
      100, 3, 87, 90, 28, 96, 7, 24, 33, 114, 168, 209, 41, 198, 164, 63,
      224, 39, 141, 12, 130, 234, 174, 180, 154, 99, 73, 229, 66, 228, 21, 183,
      200, 6, 112, 157, 65, 117, 25, 201, 170, 252, 77, 191, 42, 115, 132, 213,
      195, 175, 43, 134, 167, 177, 178, 91, 70, 211, 159, 253, 212, 15, 156, 47,
      155, 67, 239, 217, 121, 182, 83, 127, 193, 240, 35, 231, 37, 94, 181, 30,
      162, 223, 166, 254, 172, 34, 249, 226, 74, 188, 53, 202, 238, 120, 5, 107,
      81, 225, 89, 163, 242, 113, 86, 17, 106, 137, 148, 101, 140, 187, 119, 60,
      123, 40, 171, 210, 49, 222, 196, 95, 204, 207, 118, 44, 184, 216, 46, 54,
      219, 105, 179, 20, 149, 190, 98, 161, 59, 22, 102, 233, 92, 108, 109, 173,
      55, 97, 75, 185, 227, 186, 241, 160, 133, 131, 218, 71, 197, 176, 51, 250,
      150, 111, 110, 194, 246, 80, 255, 93, 169, 142, 23, 27, 151, 125, 236, 88,
      247, 31, 251, 124, 9, 13, 122, 103, 69, 135, 220, 232, 79, 29, 78, 4,
      235, 248, 243, 62, 61, 189, 138, 136, 221, 205, 11, 19, 152, 2, 147, 128,
      144, 208, 36, 52, 203, 237, 244, 206, 153, 16, 68, 64, 146, 58, 1, 38,
      18, 26, 72, 104, 245, 129, 139, 199, 214, 32, 10, 8, 0, 76, 215, 116
    });
    
    private final byte[] LVec = int_to_byte(new int[]{148, 32, 133, 16, 194, 192, 1, 251, 1, 192, 194, 16, 133, 32, 148, 1});    

    private byte[] KuzX(byte[] input1, byte[] input2)
    {
        byte[] output = new byte[16];
        for (int i = 0; i < 16; i++) {
            output[i] = (byte) (input1[i] ^ input2[i]);
        }
        return output;
    }

    private byte[][] KuzF(byte[] input1, byte[] input2, byte[] round_C) {
        byte[] state = new byte[16];
        state = KuzX(input1, round_C);
        state = KuzS(state);
        state = KuzL(state);
        return new byte[][]{KuzX(state, input2), Arrays.copyOf(input1, input1.length)};
    }

    private void KuzKeyGen(byte[] mas_key) {

        byte[][] iterNum = new byte[32][];
        for (int i = 0; i < 32; i++) {
            iterNum[i] = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte) (i + 1)};
            iterC[i] = KuzL(iterNum[i]);
        }

        byte[] A = new byte[16];
        for (int i = 0; i < 16; i++) {
            A[i] = mas_key[i];
        }
        byte[] B = new byte[16];
        int j = 0;
        for (int i = 16; i < 32; i++) {
            B[j] = mas_key[i];
            j++;
        }
        j = 0;
        iterK[0] = B;
        iterK[1] = A;

        byte[] C = new byte[16];
        byte[] D = new byte[16];
        byte[][] tmp;
        for (int i = 0; i < 4; i++) {
            tmp = KuzF(A, B, iterC[0 + 8 * i]);

            C = tmp[0];
            D = tmp[1];

            tmp = KuzF(C, D, iterC[1 + 8 * i]);
            A = tmp[0];
            B = tmp[1];
            tmp = KuzF(A, B, iterC[2 + 8 * i]);
            C = tmp[0];
            D = tmp[1];
            tmp = KuzF(C, D, iterC[3 + 8 * i]);
            A = tmp[0];
            B = tmp[1];
            tmp = KuzF(A, B, iterC[4 + 8 * i]);
            C = tmp[0];
            D = tmp[1];
            tmp = KuzF(C, D, iterC[5 + 8 * i]);
            A = tmp[0];
            B = tmp[1];
            tmp = KuzF(A, B, iterC[6 + 8 * i]);
            C = tmp[0];
            D = tmp[1];
            tmp = KuzF(C, D, iterC[7 + 8 * i]);
            A = tmp[0];
            B = tmp[1];
            iterK[2 * i + 2] = A;
            iterK[2 * i + 3] = B;
        }

    }

    private byte[] KuzS(byte[] input)
    {
        byte[] output = new byte[16];
        for (int i = 0; i < 16; i++) {
            int T = input[i];
            if (T < 0) {
                T += 256;
            }
            output[i] = Pi[T];
        }
        return output;
    }

    private byte[] KuzSReverse(byte[] input)
    {
        byte[] output = new byte[16];
        for (int i = 0; i < 16; i++) {
            int T = input[i];
            if (T < 0) {
                T += 256;
            }
            output[i] = Pi_Reverse[T];
        }
        return output;
    }

    private byte KuzMulInGF(byte a, byte b) {
        byte p = 0;
        byte counter;
        byte hi_bit_set;
        for (counter = 0; counter < 8 && a != 0 && b != 0; counter++) {
            if ((b & 1) != 0) {
                p ^= a;
            }
            hi_bit_set = (byte) (a & 0x80);
            a <<= 1;
            if (hi_bit_set != 0) {
                a ^= 0xc3;
            }
            b >>= 1;
        }
        return p;
    }

    private byte[] KuzR(byte[] input) {
        byte a_15 = 0;
        byte[] state = new byte[16];
        for (int i = 0; i <= 15; i++) {
            a_15 ^= KuzMulInGF(input[i], LVec[i]);
        }
        for (int i = 15; i > 0; i--) {
            state[i] = input[i - 1];
        }
        state[0] = a_15;
        return state;
    }

    private byte[] KuzL(byte[] input) {
        byte[] state = input;
        for (int i = 0; i < 16; i++) {
            state = KuzR(state);
        }
        return state;
    }

    private byte[] KuzRReverse(byte[] input) {
        byte a_15 = input[0];
        byte[] state = new byte[16];
        for (int i = 0; i < 15; i++) {
            state[i] = input[i + 1];
        }
        for (int i = 15; i >= 0; i--) {
            a_15 ^= KuzMulInGF(state[i], LVec[i]);
        }
        state[15] = a_15;
        return state;
    }

    private byte[] KuzLReverse(byte[] input) {
        byte[] state = input;
        for (int i = 0; i < 16; i++) {
            state = KuzRReverse(state);
        }
        return state;
    }
    public byte[] Encript(byte[] file, byte[] masterKey) {
        return KuzEncript(file,masterKey);
    }    
    private byte[] KuzEncript(byte[] file, byte[] masterKey) {
        masterKey = (lengthTo32Bytes(new String(masterKey))).getBytes();
        KuzKeyGen(masterKey);
        int NumOfBlocks; 
        int NumberOfNull;
        byte[] OriginText = file;
        byte[] encrText = new byte[0];
        if ((file.length % 16) == 0) {
            NumOfBlocks = file.length / 16;
            encrText = Arrays.copyOf(encrText, file.length);
        } else {
            NumOfBlocks = (file.length / 16) + 1;
            NumberOfNull = NumOfBlocks * 16 - file.length;
            int Startlength = file.length;
            OriginText = Arrays.copyOf(OriginText, OriginText.length + NumberOfNull);
            encrText = Arrays.copyOf(encrText, OriginText.length);
            if (NumberOfNull == 1) {
                OriginText[OriginText.length - 1] = (byte) 0x80;
            } else {
                for (int i = OriginText.length - 1; i >= 0; i--) {
                    if (i == OriginText.length - 1) {
                        OriginText[OriginText.length - 1] = (byte) (byte) -127;
                    } else if (OriginText[i] != 0) {
                        OriginText[i + 1] = 0x01;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < NumOfBlocks; i++)
        {
            byte[] block = new byte[16];
            for (int j = 0; j < 16; j++) {
                block[j] = OriginText[i * 16 + j];
            }
            for (int j = 0; j < 9; j++) {
                block = KuzX(block, iterK[j]);
                block = KuzS(block);
                block = KuzL(block);
            }
            block = KuzX(block, iterK[9]);
            for (int j = 0; j < 16; j++) {
                encrText[i * 16 + j] = block[j];
            }
        }
        return encrText;
    }
    public byte[] Decript(byte[] file, byte[] masterKey) {
        return KuzDecript(file,masterKey);
    }
    
    private byte[] KuzDecript(byte[] file, byte[] masterKey) {
        masterKey = (lengthTo32Bytes(new String(masterKey))).getBytes();
        KuzKeyGen(masterKey);
        int NumOfBlocks = file.length / 16;
        byte[] OriginText = file;
        byte[] decrText = new byte[file.length];
        for (int i = 0; i < NumOfBlocks; i++) {
            byte[] block = new byte[16];
            for (int j = 0; j < 16; j++) {
                block[j] = OriginText[i * 16 + j];
            }
            block = KuzX(block, iterK[9]);
            for (int j = 8; j >= 0; j--) {
                block = KuzLReverse(block);
                block = KuzSReverse(block);
                block = KuzX(block, iterK[j]);
            }
            for (int j = 0; j < 16; j++) {
                decrText[i * 16 + j] = block[j];
            }
            if (i == NumOfBlocks - 1 && (decrText[decrText.length - 1] == (byte) -127 || decrText[decrText.length - 1] == 0x80)) {
                if (decrText[decrText.length - 1] == (byte) -127) {
                    int Zeros = 0;
                    for (int j = decrText.length - 1; j > 0; j--) {
                        if (decrText[j] == (byte) -127 || decrText[j] == 0x01 || decrText[j] == 0) {
                            Zeros++;
                        } else {
                            break;
                        }
                    }
                    decrText = Arrays.copyOf(decrText, decrText.length - Zeros);
                }
                if (decrText[decrText.length - 1] == 0x80) {
                    decrText = Arrays.copyOf(decrText, decrText.length - 1);
                }
            }
        }
        return decrText;
    }    
}


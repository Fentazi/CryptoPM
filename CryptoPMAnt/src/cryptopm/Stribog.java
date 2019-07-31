package cryptopm;

import static cryptopm.Kuznechik.int_to_byte;
import java.util.Arrays;

public class Stribog {
 
    private final byte[] Pi =int_to_byte(new int[]{
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
    
    private final byte[] Tau = int_to_byte(new int[]{
        0, 8,  16, 24, 32, 40, 48, 56,
        1, 9,  17, 25, 33, 41, 49, 57,
        2, 10, 18, 26, 34, 42, 50, 58,
        3, 11, 19, 27, 35, 43, 51, 59,
        4, 12, 20, 28, 36, 44, 52, 60,
        5, 13, 21, 29, 37, 45, 53, 61,
        6, 14, 22, 30, 38, 46, 54, 62,
        7, 15, 23, 31, 39, 47, 55, 63
        });
    
    private final byte[][] A  = {
        {(byte) 0x8e, (byte) 0x20, (byte) 0xfa, (byte) 0xa7, (byte) 0x2b, (byte) 0xa0, (byte) 0xb4, (byte) 0x70}, {(byte) 0x47, (byte) 0x10, (byte) 0x7d, (byte) 0xdd, (byte) 0x9b, (byte) 0x50, (byte) 0x5a, (byte) 0x38}, 
        {(byte) 0xad, (byte) 0x08, (byte) 0xb0, (byte) 0xe0, (byte) 0xc3, (byte) 0x28, (byte) 0x2d, (byte) 0x1c}, {(byte) 0xd8, (byte) 0x04, (byte) 0x58, (byte) 0x70, (byte) 0xef, (byte) 0x14, (byte) 0x98, (byte) 0x0e},
        {(byte) 0x6c, (byte) 0x02, (byte) 0x2c, (byte) 0x38, (byte) 0xf9, (byte) 0x0a, (byte) 0x4c, (byte) 0x07}, {(byte) 0x36, (byte) 0x01, (byte) 0x16, (byte) 0x1c, (byte) 0xf2, (byte) 0x05, (byte) 0x26, (byte) 0x8d}, 
        {(byte) 0x1b, (byte) 0x8e, (byte) 0x0b, (byte) 0x0e, (byte) 0x79, (byte) 0x8c, (byte) 0x13, (byte) 0xc8}, {(byte) 0x83, (byte) 0x47, (byte) 0x8b, (byte) 0x07, (byte) 0xb2, (byte) 0x46, (byte) 0x87, (byte) 0x64},
        {(byte) 0xa0, (byte) 0x11, (byte) 0xd3, (byte) 0x80, (byte) 0x81, (byte) 0x8e, (byte) 0x8f, (byte) 0x40}, {(byte) 0x50, (byte) 0x86, (byte) 0xe7, (byte) 0x40, (byte) 0xce, (byte) 0x47, (byte) 0xc9, (byte) 0x20},
        {(byte) 0x28, (byte) 0x43, (byte) 0xfd, (byte) 0x20, (byte) 0x67, (byte) 0xad, (byte) 0xea, (byte) 0x10}, {(byte) 0x14, (byte) 0xaf, (byte) 0xf0, (byte) 0x10, (byte) 0xbd, (byte) 0xd8, (byte) 0x75, (byte) 0x08},
        {(byte) 0x0a, (byte) 0xd9, (byte) 0x78, (byte) 0x08, (byte) 0xd0, (byte) 0x6c, (byte) 0xb4, (byte) 0x04}, {(byte) 0x05, (byte) 0xe2, (byte) 0x3c, (byte) 0x04, (byte) 0x68, (byte) 0x36, (byte) 0x5a, (byte) 0x02}, 
        {(byte) 0x8c, (byte) 0x71, (byte) 0x1e, (byte) 0x02, (byte) 0x34, (byte) 0x1b, (byte) 0x2d, (byte) 0x01}, {(byte) 0x46, (byte) 0xb6, (byte) 0x0f, (byte) 0x01, (byte) 0x1a, (byte) 0x83, (byte) 0x98, (byte) 0x8e},
        {(byte) 0x90, (byte) 0xda, (byte) 0xb5, (byte) 0x2a, (byte) 0x38, (byte) 0x7a, (byte) 0xe7, (byte) 0x6f}, {(byte) 0x48, (byte) 0x6d, (byte) 0xd4, (byte) 0x15, (byte) 0x1c, (byte) 0x3d, (byte) 0xfd, (byte) 0xb9}, 
        {(byte) 0x24, (byte) 0xb8, (byte) 0x6a, (byte) 0x84, (byte) 0x0e, (byte) 0x90, (byte) 0xf0, (byte) 0xd2}, {(byte) 0x12, (byte) 0x5c, (byte) 0x35, (byte) 0x42, (byte) 0x07, (byte) 0x48, (byte) 0x78, (byte) 0x69},
        {(byte) 0x09, (byte) 0x2e, (byte) 0x94, (byte) 0x21, (byte) 0x8d, (byte) 0x24, (byte) 0x3c, (byte) 0xba}, {(byte) 0x8a, (byte) 0x17, (byte) 0x4a, (byte) 0x9e, (byte) 0xc8, (byte) 0x12, (byte) 0x1e, (byte) 0x5d},
        {(byte) 0x45, (byte) 0x85, (byte) 0x25, (byte) 0x4f, (byte) 0x64, (byte) 0x09, (byte) 0x0f, (byte) 0xa0}, {(byte) 0xac, (byte) 0xcc, (byte) 0x9c, (byte) 0xa9, (byte) 0x32, (byte) 0x8a, (byte) 0x89, (byte) 0x50},
        {(byte) 0x9d, (byte) 0x4d, (byte) 0xf0, (byte) 0x5d, (byte) 0x5f, (byte) 0x66, (byte) 0x14, (byte) 0x51}, {(byte) 0xc0, (byte) 0xa8, (byte) 0x78, (byte) 0xa0, (byte) 0xa1, (byte) 0x33, (byte) 0x0a, (byte) 0xa6}, 
        {(byte) 0x60, (byte) 0x54, (byte) 0x3c, (byte) 0x50, (byte) 0xde, (byte) 0x97, (byte) 0x05, (byte) 0x53}, {(byte) 0x30, (byte) 0x2a, (byte) 0x1e, (byte) 0x28, (byte) 0x6f, (byte) 0xc5, (byte) 0x8c, (byte) 0xa7},
        {(byte) 0x18, (byte) 0x15, (byte) 0x0f, (byte) 0x14, (byte) 0xb9, (byte) 0xec, (byte) 0x46, (byte) 0xdd}, {(byte) 0x0c, (byte) 0x84, (byte) 0x89, (byte) 0x0a, (byte) 0xd2, (byte) 0x76, (byte) 0x23, (byte) 0xe0}, 
        {(byte) 0x06, (byte) 0x42, (byte) 0xca, (byte) 0x05, (byte) 0x69, (byte) 0x3b, (byte) 0x9f, (byte) 0x70}, {(byte) 0x03, (byte) 0x21, (byte) 0x65, (byte) 0x8c, (byte) 0xba, (byte) 0x93, (byte) 0xc1, (byte) 0x38},
        {(byte) 0x86, (byte) 0x27, (byte) 0x5d, (byte) 0xf0, (byte) 0x9c, (byte) 0xe8, (byte) 0xaa, (byte) 0xa8}, {(byte) 0x43, (byte) 0x9d, (byte) 0xa0, (byte) 0x78, (byte) 0x4e, (byte) 0x74, (byte) 0x55, (byte) 0x54},
        {(byte) 0xaf, (byte) 0xc0, (byte) 0x50, (byte) 0x3c, (byte) 0x27, (byte) 0x3a, (byte) 0xa4, (byte) 0x2a}, {(byte) 0xd9, (byte) 0x60, (byte) 0x28, (byte) 0x1e, (byte) 0x9d, (byte) 0x1d, (byte) 0x52, (byte) 0x15},
        {(byte) 0xe2, (byte) 0x30, (byte) 0x14, (byte) 0x0f, (byte) 0xc0, (byte) 0x80, (byte) 0x29, (byte) 0x84}, {(byte) 0x71, (byte) 0x18, (byte) 0x0a, (byte) 0x89, (byte) 0x60, (byte) 0x40, (byte) 0x9a, (byte) 0x42},
        {(byte) 0xb6, (byte) 0x0c, (byte) 0x05, (byte) 0xca, (byte) 0x30, (byte) 0x20, (byte) 0x4d, (byte) 0x21}, {(byte) 0x5b, (byte) 0x06, (byte) 0x8c, (byte) 0x65, (byte) 0x18, (byte) 0x10, (byte) 0xa8, (byte) 0x9e},
        {(byte) 0x45, (byte) 0x6c, (byte) 0x34, (byte) 0x88, (byte) 0x7a, (byte) 0x38, (byte) 0x05, (byte) 0xb9}, {(byte) 0xac, (byte) 0x36, (byte) 0x1a, (byte) 0x44, (byte) 0x3d, (byte) 0x1c, (byte) 0x8c, (byte) 0xd2}, 
        {(byte) 0x56, (byte) 0x1b, (byte) 0x0d, (byte) 0x22, (byte) 0x90, (byte) 0x0e, (byte) 0x46, (byte) 0x69}, {(byte) 0x2b, (byte) 0x83, (byte) 0x88, (byte) 0x11, (byte) 0x48, (byte) 0x07, (byte) 0x23, (byte) 0xba},
        {(byte) 0x9b, (byte) 0xcf, (byte) 0x44, (byte) 0x86, (byte) 0x24, (byte) 0x8d, (byte) 0x9f, (byte) 0x5d}, {(byte) 0xc3, (byte) 0xe9, (byte) 0x22, (byte) 0x43, (byte) 0x12, (byte) 0xc8, (byte) 0xc1, (byte) 0xa0}, 
        {(byte) 0xef, (byte) 0xfa, (byte) 0x11, (byte) 0xaf, (byte) 0x09, (byte) 0x64, (byte) 0xee, (byte) 0x50}, {(byte) 0xf9, (byte) 0x7d, (byte) 0x86, (byte) 0xd9, (byte) 0x8a, (byte) 0x32, (byte) 0x77, (byte) 0x28},
        {(byte) 0xe4, (byte) 0xfa, (byte) 0x20, (byte) 0x54, (byte) 0xa8, (byte) 0x0b, (byte) 0x32, (byte) 0x9c}, {(byte) 0x72, (byte) 0x7d, (byte) 0x10, (byte) 0x2a, (byte) 0x54, (byte) 0x8b, (byte) 0x19, (byte) 0x4e}, 
        {(byte) 0x39, (byte) 0xb0, (byte) 0x08, (byte) 0x15, (byte) 0x2a, (byte) 0xcb, (byte) 0x82, (byte) 0x27}, {(byte) 0x92, (byte) 0x58, (byte) 0x04, (byte) 0x84, (byte) 0x15, (byte) 0xeb, (byte) 0x41, (byte) 0x9d},
        {(byte) 0x49, (byte) 0x2c, (byte) 0x02, (byte) 0x42, (byte) 0x84, (byte) 0xfb, (byte) 0xae, (byte) 0xc0}, {(byte) 0xaa, (byte) 0x16, (byte) 0x01, (byte) 0x21, (byte) 0x42, (byte) 0xf3, (byte) 0x57, (byte) 0x60}, 
        {(byte) 0x55, (byte) 0x0b, (byte) 0x8e, (byte) 0x9e, (byte) 0x21, (byte) 0xf7, (byte) 0xa5, (byte) 0x30}, {(byte) 0xa4, (byte) 0x8b, (byte) 0x47, (byte) 0x4f, (byte) 0x9e, (byte) 0xf5, (byte) 0xdc, (byte) 0x18},
        {(byte) 0x70, (byte) 0xa6, (byte) 0xa5, (byte) 0x6e, (byte) 0x24, (byte) 0x40, (byte) 0x59, (byte) 0x8e}, {(byte) 0x38, (byte) 0x53, (byte) 0xdc, (byte) 0x37, (byte) 0x12, (byte) 0x20, (byte) 0xa2, (byte) 0x47},
        {(byte) 0x1c, (byte) 0xa7, (byte) 0x6e, (byte) 0x95, (byte) 0x09, (byte) 0x10, (byte) 0x51, (byte) 0xad}, {(byte) 0x0e, (byte) 0xdd, (byte) 0x37, (byte) 0xc4, (byte) 0x8a, (byte) 0x08, (byte) 0xa6, (byte) 0xd8},
        {(byte) 0x07, (byte) 0xe0, (byte) 0x95, (byte) 0x62, (byte) 0x45, (byte) 0x04, (byte) 0x53, (byte) 0x6c}, {(byte) 0x8d, (byte) 0x70, (byte) 0xc4, (byte) 0x31, (byte) 0xac, (byte) 0x02, (byte) 0xa7, (byte) 0x36},
        {(byte) 0xc8, (byte) 0x38, (byte) 0x62, (byte) 0x96, (byte) 0x56, (byte) 0x01, (byte) 0xdd, (byte) 0x1b}, {(byte) 0x64, (byte) 0x1c, (byte) 0x31, (byte) 0x4b, (byte) 0x2b, (byte) 0x8e, (byte) 0xe0, (byte) 0x83}
        };  
    
    private final byte [][] C = {
        {
        (byte) 0xb1,(byte) 0x08,(byte) 0x5b,(byte) 0xda,(byte) 0x1e,(byte) 0xca,(byte) 0xda,(byte) 0xe9,(byte) 0xeb,(byte) 0xcb,(byte) 0x2f,(byte) 0x81,(byte) 0xc0,(byte) 0x65,(byte) 0x7c,(byte) 0x1f,
        (byte) 0x2f,(byte) 0x6a,(byte) 0x76,(byte) 0x43,(byte) 0x2e,(byte) 0x45,(byte) 0xd0,(byte) 0x16,(byte) 0x71,(byte) 0x4e,(byte) 0xb8,(byte) 0x8d,(byte) 0x75,(byte) 0x85,(byte) 0xc4,(byte) 0xfc,
        (byte) 0x4b,(byte) 0x7c,(byte) 0xe0,(byte) 0x91,(byte) 0x92,(byte) 0x67,(byte) 0x69,(byte) 0x01,(byte) 0xa2,(byte) 0x42,(byte) 0x2a,(byte) 0x08,(byte) 0xa4,(byte) 0x60,(byte) 0xd3,(byte) 0x15,
        (byte) 0x05,(byte) 0x76,(byte) 0x74,(byte) 0x36,(byte) 0xcc,(byte) 0x74,(byte) 0x4d,(byte) 0x23,(byte) 0xdd,(byte) 0x80,(byte) 0x65,(byte) 0x59,(byte) 0xf2,(byte) 0xa6,(byte) 0x45,(byte) 0x07
        },
        {
        (byte) 0x6f,(byte) 0xa3,(byte) 0xb5,(byte) 0x8a,(byte) 0xa9,(byte) 0x9d,(byte) 0x2f,(byte) 0x1a,(byte) 0x4f,(byte) 0xe3,(byte) 0x9d,(byte) 0x46,(byte) 0x0f,(byte) 0x70,(byte) 0xb5,(byte) 0xd7,
        (byte) 0xf3,(byte) 0xfe,(byte) 0xea,(byte) 0x72,(byte) 0x0a,(byte) 0x23,(byte) 0x2b,(byte) 0x98,(byte) 0x61,(byte) 0xd5,(byte) 0x5e,(byte) 0x0f,(byte) 0x16,(byte) 0xb5,(byte) 0x01,(byte) 0x31,        
        (byte) 0x9a,(byte) 0xb5,(byte) 0x17,(byte) 0x6b,(byte) 0x12,(byte) 0xd6,(byte) 0x99,(byte) 0x58,(byte) 0x5c,(byte) 0xb5,(byte) 0x61,(byte) 0xc2,(byte) 0xdb,(byte) 0x0a,(byte) 0xa7,(byte) 0xca,
        (byte) 0x55,(byte) 0xdd,(byte) 0xa2,(byte) 0x1b,(byte) 0xd7,(byte) 0xcb,(byte) 0xcd,(byte) 0x56,(byte) 0xe6,(byte) 0x79,(byte) 0x04,(byte) 0x70,(byte) 0x21,(byte) 0xb1,(byte) 0x9b,(byte) 0xb7
        },
        {
        (byte) 0xf5,(byte) 0x74,(byte) 0xdc,(byte) 0xac,(byte) 0x2b,(byte) 0xce,(byte) 0x2f,(byte) 0xc7,(byte) 0x0a,(byte) 0x39,(byte) 0xfc,(byte) 0x28,(byte) 0x6a,(byte) 0x3d,(byte) 0x84,(byte) 0x35,
        (byte) 0x06,(byte) 0xf1,(byte) 0x5e,(byte) 0x5f,(byte) 0x52,(byte) 0x9c,(byte) 0x1f,(byte) 0x8b,(byte) 0xf2,(byte) 0xea,(byte) 0x75,(byte) 0x14,(byte) 0xb1,(byte) 0x29,(byte) 0x7b,(byte) 0x7b,
        (byte) 0xd3,(byte) 0xe2,(byte) 0x0f,(byte) 0xe4,(byte) 0x90,(byte) 0x35,(byte) 0x9e,(byte) 0xb1,(byte) 0xc1,(byte) 0xc9,(byte) 0x3a,(byte) 0x37,(byte) 0x60,(byte) 0x62,(byte) 0xdb,(byte) 0x09,
        (byte) 0xc2,(byte) 0xb6,(byte) 0xf4,(byte) 0x43,(byte) 0x86,(byte) 0x7a,(byte) 0xdb,(byte) 0x31,(byte) 0x99,(byte) 0x1e,(byte) 0x96,(byte) 0xf5,(byte) 0x0a,(byte) 0xba,(byte) 0x0a,(byte) 0xb2
        },
        {
        (byte) 0xef,(byte) 0x1f,(byte) 0xdf,(byte) 0xb3,(byte) 0xe8,(byte) 0x15,(byte) 0x66,(byte) 0xd2,(byte) 0xf9,(byte) 0x48,(byte) 0xe1,(byte) 0xa0,(byte) 0x5d,(byte) 0x71,(byte) 0xe4,(byte) 0xdd,
        (byte) 0x48,(byte) 0x8e,(byte) 0x85,(byte) 0x7e,(byte) 0x33,(byte) 0x5c,(byte) 0x3c,(byte) 0x7d,(byte) 0x9d,(byte) 0x72,(byte) 0x1c,(byte) 0xad,(byte) 0x68,(byte) 0x5e,(byte) 0x35,(byte) 0x3f,
        (byte) 0xa9,(byte) 0xd7,(byte) 0x2c,(byte) 0x82,(byte) 0xed,(byte) 0x03,(byte) 0xd6,(byte) 0x75,(byte) 0xd8,(byte) 0xb7,(byte) 0x13,(byte) 0x33,(byte) 0x93,(byte) 0x52,(byte) 0x03,(byte) 0xbe,
        (byte) 0x34,(byte) 0x53,(byte) 0xea,(byte) 0xa1,(byte) 0x93,(byte) 0xe8,(byte) 0x37,(byte) 0xf1,(byte) 0x22,(byte) 0x0c,(byte) 0xbe,(byte) 0xbc,(byte) 0x84,(byte) 0xe3,(byte) 0xd1,(byte) 0x2e
        },
        {
        (byte) 0x4b,(byte) 0xea,(byte) 0x6b,(byte) 0xac,(byte) 0xad,(byte) 0x47,(byte) 0x47,(byte) 0x99,(byte) 0x9a,(byte) 0x3f,(byte) 0x41,(byte) 0x0c,(byte) 0x6c,(byte) 0xa9,(byte) 0x23,(byte) 0x63,
        (byte) 0x7f,(byte) 0x15,(byte) 0x1c,(byte) 0x1f,(byte) 0x16,(byte) 0x86,(byte) 0x10,(byte) 0x4a,(byte) 0x35,(byte) 0x9e,(byte) 0x35,(byte) 0xd7,(byte) 0x80,(byte) 0x0f,(byte) 0xff,(byte) 0xbd,
        (byte) 0xbf,(byte) 0xcd,(byte) 0x17,(byte) 0x47,(byte) 0x25,(byte) 0x3a,(byte) 0xf5,(byte) 0xa3,(byte) 0xdf,(byte) 0xff,(byte) 0x00,(byte) 0xb7,(byte) 0x23,(byte) 0x27,(byte) 0x1a,(byte) 0x16,
        (byte) 0x7a,(byte) 0x56,(byte) 0xa2,(byte) 0x7e,(byte) 0xa9,(byte) 0xea,(byte) 0x63,(byte) 0xf5,(byte) 0x60,(byte) 0x17,(byte) 0x58,(byte) 0xfd,(byte) 0x7c,(byte) 0x6c,(byte) 0xfe,(byte) 0x57
        },
        {
        (byte) 0xae,(byte) 0x4f,(byte) 0xae,(byte) 0xae,(byte) 0x1d,(byte) 0x3a,(byte) 0xd3,(byte) 0xd9,(byte) 0x6f,(byte) 0xa4,(byte) 0xc3,(byte) 0x3b,(byte) 0x7a,(byte) 0x30,(byte) 0x39,(byte) 0xc0,
        (byte) 0x2d,(byte) 0x66,(byte) 0xc4,(byte) 0xf9,(byte) 0x51,(byte) 0x42,(byte) 0xa4,(byte) 0x6c,(byte) 0x18,(byte) 0x7f,(byte) 0x9a,(byte) 0xb4,(byte) 0x9a,(byte) 0xf0,(byte) 0x8e,(byte) 0xc6,
        (byte) 0xcf,(byte) 0xfa,(byte) 0xa6,(byte) 0xb7,(byte) 0x1c,(byte) 0x9a,(byte) 0xb7,(byte) 0xb4,(byte) 0x0a,(byte) 0xf2,(byte) 0x1f,(byte) 0x66,(byte) 0xc2,(byte) 0xbe,(byte) 0xc6,(byte) 0xb6,
        (byte) 0xbf,(byte) 0x71,(byte) 0xc5,(byte) 0x72,(byte) 0x36,(byte) 0x90,(byte) 0x4f,(byte) 0x35,(byte) 0xfa,(byte) 0x68,(byte) 0x40,(byte) 0x7a,(byte) 0x46,(byte) 0x64,(byte) 0x7d,(byte) 0x6e
        },
        {
        (byte) 0xf4,(byte) 0xc7,(byte) 0x0e,(byte) 0x16,(byte) 0xee,(byte) 0xaa,(byte) 0xc5,(byte) 0xec,(byte) 0x51,(byte) 0xac,(byte) 0x86,(byte) 0xfe,(byte) 0xbf,(byte) 0x24,(byte) 0x09,(byte) 0x54,
        (byte) 0x39,(byte) 0x9e,(byte) 0xc6,(byte) 0xc7,(byte) 0xe6,(byte) 0xbf,(byte) 0x87,(byte) 0xc9,(byte) 0xd3,(byte) 0x47,(byte) 0x3e,(byte) 0x33,(byte) 0x19,(byte) 0x7a,(byte) 0x93,(byte) 0xc9,
        (byte) 0x09,(byte) 0x92,(byte) 0xab,(byte) 0xc5,(byte) 0x2d,(byte) 0x82,(byte) 0x2c,(byte) 0x37,(byte) 0x06,(byte) 0x47,(byte) 0x69,(byte) 0x83,(byte) 0x28,(byte) 0x4a,(byte) 0x05,(byte) 0x04,
        (byte) 0x35,(byte) 0x17,(byte) 0x45,(byte) 0x4c,(byte) 0xa2,(byte) 0x3c,(byte) 0x4a,(byte) 0xf3,(byte) 0x88,(byte) 0x86,(byte) 0x56,(byte) 0x4d,(byte) 0x3a,(byte) 0x14,(byte) 0xd4,(byte) 0x93
        },
        {
        (byte) 0x9b,(byte) 0x1f,(byte) 0x5b,(byte) 0x42,(byte) 0x4d,(byte) 0x93,(byte) 0xc9,(byte) 0xa7,(byte) 0x03,(byte) 0xe7,(byte) 0xaa,(byte) 0x02,(byte) 0x0c,(byte) 0x6e,(byte) 0x41,(byte) 0x41,
        (byte) 0x4e,(byte) 0xb7,(byte) 0xf8,(byte) 0x71,(byte) 0x9c,(byte) 0x36,(byte) 0xde,(byte) 0x1e,(byte) 0x89,(byte) 0xb4,(byte) 0x44,(byte) 0x3b,(byte) 0x4d,(byte) 0xdb,(byte) 0xc4,(byte) 0x9a,
        (byte) 0xf4,(byte) 0x89,(byte) 0x2b,(byte) 0xcb,(byte) 0x92,(byte) 0x9b,(byte) 0x06,(byte) 0x90,(byte) 0x69,(byte) 0xd1,(byte) 0x8d,(byte) 0x2b,(byte) 0xd1,(byte) 0xa5,(byte) 0xc4,(byte) 0x2f,
        (byte) 0x36,(byte) 0xac,(byte) 0xc2,(byte) 0x35,(byte) 0x59,(byte) 0x51,(byte) 0xa8,(byte) 0xd9,(byte) 0xa4,(byte) 0x7f,(byte) 0x0d,(byte) 0xd4,(byte) 0xbf,(byte) 0x02,(byte) 0xe7,(byte) 0x1e
        },
        {
        (byte) 0x37,(byte) 0x8f,(byte) 0x5a,(byte) 0x54,(byte) 0x16,(byte) 0x31,(byte) 0x22,(byte) 0x9b,(byte) 0x94,(byte) 0x4c,(byte) 0x9a,(byte) 0xd8,(byte) 0xec,(byte) 0x16,(byte) 0x5f,(byte) 0xde,
        (byte) 0x3a,(byte) 0x7d,(byte) 0x3a,(byte) 0x1b,(byte) 0x25,(byte) 0x89,(byte) 0x42,(byte) 0x24,(byte) 0x3c,(byte) 0xd9,(byte) 0x55,(byte) 0xb7,(byte) 0xe0,(byte) 0x0d,(byte) 0x09,(byte) 0x84,
        (byte) 0x80,(byte) 0x0a,(byte) 0x44,(byte) 0x0b,(byte) 0xdb,(byte) 0xb2,(byte) 0xce,(byte) 0xb1,(byte) 0x7b,(byte) 0x2b,(byte) 0x8a,(byte) 0x9a,(byte) 0xa6,(byte) 0x07,(byte) 0x9c,(byte) 0x54,
        (byte) 0x0e,(byte) 0x38,(byte) 0xdc,(byte) 0x92,(byte) 0xcb,(byte) 0x1f,(byte) 0x2a,(byte) 0x60,(byte) 0x72,(byte) 0x61,(byte) 0x44,(byte) 0x51,(byte) 0x83,(byte) 0x23,(byte) 0x5a,(byte) 0xdb
        },
        {
        (byte) 0xab,(byte) 0xbe,(byte) 0xde,(byte) 0xa6,(byte) 0x80,(byte) 0x05,(byte) 0x6f,(byte) 0x52,(byte) 0x38,(byte) 0x2a,(byte) 0xe5,(byte) 0x48,(byte) 0xb2,(byte) 0xe4,(byte) 0xf3,(byte) 0xf3,
        (byte) 0x89,(byte) 0x41,(byte) 0xe7,(byte) 0x1c,(byte) 0xff,(byte) 0x8a,(byte) 0x78,(byte) 0xdb,(byte) 0x1f,(byte) 0xff,(byte) 0xe1,(byte) 0x8a,(byte) 0x1b,(byte) 0x33,(byte) 0x61,(byte) 0x03,
        (byte) 0x9f,(byte) 0xe7,(byte) 0x67,(byte) 0x02,(byte) 0xaf,(byte) 0x69,(byte) 0x33,(byte) 0x4b,(byte) 0x7a,(byte) 0x1e,(byte) 0x6c,(byte) 0x30,(byte) 0x3b,(byte) 0x76,(byte) 0x52,(byte) 0xf4,
        (byte) 0x36,(byte) 0x98,(byte) 0xfa,(byte) 0xd1,(byte) 0x15,(byte) 0x3b,(byte) 0xb6,(byte) 0xc3,(byte) 0x74,(byte) 0xb4,(byte) 0xc7,(byte) 0xfb,(byte) 0x98,(byte) 0x45,(byte) 0x9c,(byte) 0xed
        },
        {
        (byte) 0x7b,(byte) 0xcd,(byte) 0x9e,(byte) 0xd0,(byte) 0xef,(byte) 0xc8,(byte) 0x89,(byte) 0xfb,(byte) 0x30,(byte) 0x02,(byte) 0xc6,(byte) 0xcd,(byte) 0x63,(byte) 0x5a,(byte) 0xfe,(byte) 0x94,
        (byte) 0xd8,(byte) 0xfa,(byte) 0x6b,(byte) 0xbb,(byte) 0xeb,(byte) 0xab,(byte) 0x07,(byte) 0x61,(byte) 0x20,(byte) 0x01,(byte) 0x80,(byte) 0x21,(byte) 0x14,(byte) 0x84,(byte) 0x66,(byte) 0x79,
        (byte) 0x8a,(byte) 0x1d,(byte) 0x71,(byte) 0xef,(byte) 0xea,(byte) 0x48,(byte) 0xb9,(byte) 0xca,(byte) 0xef,(byte) 0xba,(byte) 0xcd,(byte) 0x1d,(byte) 0x7d,(byte) 0x47,(byte) 0x6e,(byte) 0x98,
        (byte) 0xde,(byte) 0xa2,(byte) 0x59,(byte) 0x4a,(byte) 0xc0,(byte) 0x6f,(byte) 0xd8,(byte) 0x5d,(byte) 0x6b,(byte) 0xca,(byte) 0xa4,(byte) 0xcd,(byte) 0x81,(byte) 0xf3,(byte) 0x2d,(byte) 0x1b
        },
        {
        (byte) 0x37,(byte) 0x8e,(byte) 0xe7,(byte) 0x67,(byte) 0xf1,(byte) 0x16,(byte) 0x31,(byte) 0xba,(byte) 0xd2,(byte) 0x13,(byte) 0x80,(byte) 0xb0,(byte) 0x04,(byte) 0x49,(byte) 0xb1,(byte) 0x7a,
        (byte) 0xcd,(byte) 0xa4,(byte) 0x3c,(byte) 0x32,(byte) 0xbc,(byte) 0xdf,(byte) 0x1d,(byte) 0x77,(byte) 0xf8,(byte) 0x20,(byte) 0x12,(byte) 0xd4,(byte) 0x30,(byte) 0x21,(byte) 0x9f,(byte) 0x9b,
        (byte) 0x5d,(byte) 0x80,(byte) 0xef,(byte) 0x9d,(byte) 0x18,(byte) 0x91,(byte) 0xcc,(byte) 0x86,(byte) 0xe7,(byte) 0x1d,(byte) 0xa4,(byte) 0xaa,(byte) 0x88,(byte) 0xe1,(byte) 0x28,(byte) 0x52,
        (byte) 0xfa,(byte) 0xf4,(byte) 0x17,(byte) 0xd5,(byte) 0xd9,(byte) 0xb2,(byte) 0x1b,(byte) 0x99,(byte) 0x48,(byte) 0xbc,(byte) 0x92,(byte) 0x4a,(byte) 0xf1,(byte) 0x1b,(byte) 0xd7,(byte) 0x20
        }
        };


    public byte[] getHash(byte[] message) {
        return StribogHash(message);
    }
    
    private byte[] StribogHash(byte[] message)
    {
        byte[] h = new byte[64];
        Arrays.fill(h, (byte)0x01);

        byte[] N = new byte[64];
        Arrays.fill(N, (byte)0x00);
        
        byte[] Sigma = new byte[64];
        Arrays.fill(Sigma, (byte)0x00);
        
        byte[] v_0 = new byte[64];
        Arrays.fill(v_0, (byte)0x00);
        
        byte[] v_512 = new byte[64];
        Arrays.fill(v_512, (byte)0x00);
        v_512[63] = (byte)0x02;
        
        int length = message.length * 8;
        int inc = 0;
        
        while(length >= 512)
        {
            inc++;
            byte[] tmp = Arrays.copyOfRange(message, message.length - 64*(inc), message.length - (inc-1)*64);
            h = StribG(N, tmp, h);
            N = addModule(N, v_512);
            Sigma = addModule(Sigma, tmp);
            length -=512;
        }
        
        message = Arrays.copyOf(message, message.length - 64*inc);
        
        if(message.length < 64)
        {
            byte[] tmpMessage = new byte[64];
            Arrays.fill(tmpMessage, (byte) 0x00);
            tmpMessage[64 - message.length - 1] = (byte) 0x01;
            
            for(int i = 64 - message.length; i < 64; i++)
                tmpMessage[i] = message[i + message.length - 64];
            
            message = tmpMessage;
        }
            
        h = StribG(N, message, h);
        
        byte[] NMessage = new byte[64];
        Arrays.fill(NMessage, (byte) 0x00);
        
        inc = 0;
        while(length > 0)
        {
            NMessage[63 - inc] = (byte) (length & 0xff);
            length >>= 8;
            inc++;
        }
        
        N = addModule(N, NMessage);
        Sigma = addModule(Sigma, message);

        h = StribG(v_0, N, h);
        h = StribG(v_0, Sigma, h);
        
        return Arrays.copyOf(h, 32);
    }
    
    private byte[] addModule(byte[] a, byte[] b)
    {        
        byte[] result = new byte[64];
        int t = 0;
        
        for(int i = 63; i >= 0; i--)
        {
            t = (a[i] & 0xff) + (b[i] & 0xff)  + (t >> 8);
            result[i] = (byte) (t & 0xff);
        }
        return result;
    }
    
    private byte[] StribX(byte[] a, byte[] b)
    {
        byte[] result = new byte[a.length];
        for(int i = 0; i < a.length; i++)
            result[i] = (byte) (a[i] ^ b[i]);
        return result;
    }
    
    private byte[] StribG(byte [] N, byte[] m, byte[] h)
    {
        
        byte[] K;
        K = StribX(h, N);
        K = SrtibS(K);
        K = StribP(K);
        K = StribL(K);
        
        byte[] t = StribE(K, m);
        t = StribX(h, t);
        byte[] result = StribX(t, m);
            
        return result;
    }
    
    private byte[] StribE(byte[] K, byte[] m)
    {
        byte[] state;
        
        state = StribX(K, m);
        for(int i = 0; i < 12; i++)
        {
            state = SrtibS(state);
            state = StribP(state);
            state = StribL(state);

            K = KeyShedule(K, i);
            state = StribX(state, K);
        }
        
        return state;
    }
    

    private byte[] KeyShedule(byte[] K, int i)
    {
        K = StribX(K, C[i]);
        
        K = SrtibS(K);
        K = StribP(K);
        K = StribL(K);
        
        return K;
    }

    private byte[] SrtibS(byte[] a)
    {        
        byte[] result = new byte[64];
        
        for(int i = 0; i < 64; i++)
            result[i] = Pi[(a[i] & 0xFF)];
        
        return result;
    }
    
    private byte[] StribP(byte[] a)
    {
        byte[] result = new byte[64];
        
        for(int i = 0; i < 64; i++)
            result[i] = a[Tau[i]];
        
        return result;
    }
    
    private byte[] StribL(byte[] a)
    {
        byte[] result = new byte[64];
        for(int i = 0; i < 8; i++)
        {
            for(int k = 0; k < 8; k++)
            {
                if((a[i*8+k] & (0x80)) != 0)
                {
                    result[8*i] ^= A[k*8+0][0];
                    result[8*i+1] ^= A[k*8+0][1];
                    result[8*i+2] ^= A[k*8+0][2];
                    result[8*i+3] ^= A[k*8+0][3];
                    result[8*i+4] ^= A[k*8+0][4];
                    result[8*i+5] ^= A[k*8+0][5];
                    result[8*i+6] ^= A[k*8+0][6];
                    result[8*i+7] ^= A[k*8+0][7];
                }
                if((a[i*8+k] & (0x40)) != 0)
                    {
                    result[8*i] ^= A[k*8+1][0];
                    result[8*i+1] ^= A[k*8+1][1];
                    result[8*i+2] ^= A[k*8+1][2];
                    result[8*i+3] ^= A[k*8+1][3];
                    result[8*i+4] ^= A[k*8+1][4];
                    result[8*i+5] ^= A[k*8+1][5];
                    result[8*i+6] ^= A[k*8+1][6];
                    result[8*i+7] ^= A[k*8+1][7];
                }
                if((a[i*8+k] & (0x20)) != 0)
                    {
                    result[8*i] ^= A[k*8+2][0];
                    result[8*i+1] ^= A[k*8+2][1];
                    result[8*i+2] ^= A[k*8+2][2];
                    result[8*i+3] ^= A[k*8+2][3];
                    result[8*i+4] ^= A[k*8+2][4];
                    result[8*i+5] ^= A[k*8+2][5];
                    result[8*i+6] ^= A[k*8+2][6];
                    result[8*i+7] ^= A[k*8+2][7];
                }
                if((a[i*8+k] & (0x10)) != 0)
                    {
                    result[8*i] ^= A[k*8+3][0];
                    result[8*i+1] ^= A[k*8+3][1];
                    result[8*i+2] ^= A[k*8+3][2];
                    result[8*i+3] ^= A[k*8+3][3];
                    result[8*i+4] ^= A[k*8+3][4];
                    result[8*i+5] ^= A[k*8+3][5];
                    result[8*i+6] ^= A[k*8+3][6];
                    result[8*i+7] ^= A[k*8+3][7];
                }
                if((a[i*8+k] & (0x8)) != 0)
                    {
                    result[8*i] ^= A[k*8+4][0];
                    result[8*i+1] ^= A[k*8+4][1];
                    result[8*i+2] ^= A[k*8+4][2];
                    result[8*i+3] ^= A[k*8+4][3];
                    result[8*i+4] ^= A[k*8+4][4];
                    result[8*i+5] ^= A[k*8+4][5];
                    result[8*i+6] ^= A[k*8+4][6];
                    result[8*i+7] ^= A[k*8+4][7];
                }
                if((a[i*8+k] & (0x4)) != 0)
                    {
                    result[8*i] ^= A[k*8+5][0];
                    result[8*i+1] ^= A[k*8+5][1];
                    result[8*i+2] ^= A[k*8+5][2];
                    result[8*i+3] ^= A[k*8+5][3];
                    result[8*i+4] ^= A[k*8+5][4];
                    result[8*i+5] ^= A[k*8+5][5];
                    result[8*i+6] ^= A[k*8+5][6];
                    result[8*i+7] ^= A[k*8+5][7];
                }
                if((a[i*8+k] & (0x2)) != 0)
                    {
                    result[8*i] ^= A[k*8+6][0];
                    result[8*i+1] ^= A[k*8+6][1];
                    result[8*i+2] ^= A[k*8+6][2];
                    result[8*i+3] ^= A[k*8+6][3];
                    result[8*i+4] ^= A[k*8+6][4];
                    result[8*i+5] ^= A[k*8+6][5];
                    result[8*i+6] ^= A[k*8+6][6];
                    result[8*i+7] ^= A[k*8+6][7];
                }
                if((a[i*8+k] & (0x1)) != 0)
                    {
                    result[8*i] ^= A[k*8+7][0];
                    result[8*i+1] ^= A[k*8+7][1];
                    result[8*i+2] ^= A[k*8+7][2];
                    result[8*i+3] ^= A[k*8+7][3];
                    result[8*i+4] ^= A[k*8+7][4];
                    result[8*i+5] ^= A[k*8+7][5];
                    result[8*i+6] ^= A[k*8+7][6];
                    result[8*i+7] ^= A[k*8+7][7];
                }
            }
        }
        return result;
    }
}

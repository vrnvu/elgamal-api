package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class ElGamal implements Cryptosystem {

    private BigInteger privateKey;
    private BigInteger generator;
    private BigInteger order;
    private BigInteger grade;


    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);
    private Random r;

    /*
           BigInteger dos= BigInteger.ONE.add(BigInteger.ONE);
        BigInteger aux= BigInteger.ONE;
        Boolean correcte = false;
        Random rnd = new SecureRandom();
        while(!correcte){
            q = new BigInteger(grauSeguretat, 20, rnd);
            grau_Z= dos.multiply(q).add(BigInteger.ONE);
            correcte=grau_Z.isProbablePrime(20);
            //System.out.println("perdent temps");
        }
        correcte= false;
        while(!correcte){
            g= new BigInteger(25,rnd);
            g=g.mod(grau_Z.subtract(BigInteger.ONE));
            if(!g.modPow(dos, grau_Z).equals(BigInteger.ONE)){
                correcte=true;
            }
            //System.out.println("perdo temps");
        }

        privatekey= new BigInteger(100,rnd);
        privatekey= privatekey.mod(q);
        encriptador= new EncriptadorGamalAdd(getClauPublica(),g,grau_Z,q);
        resCalculats = new BigInteger[10];
        for(int i=0; i<10; i++){
            aux= aux.add(BigInteger.ONE);
            resCalculats[i]=g.modPow(aux, grau_Z);
        }
     */


    // https://crypto.stackexchange.com/questions/1451/elgamal-multiplicative-cyclic-group-and-key-generation?rq=1
    public ElGamal() {
        r = new SecureRandom();
        generateCyclicGroup();
        generatePrivateKey();
        privateKey = null;
        generator = null;
        order = null;
        grade = null;

    }

    private void generatePrivateKey() {

    }

    private void generateCyclicGroup() {
        // BigInteger(int bitLength, int certainty, Random rnd)
        // isProbablePrime(int certainty)

        boolean gradeIsPrime;
        do {
            order = new BigInteger(4, 30, r);
            grade = TWO.multiply(order).add(BigInteger.ONE);
            gradeIsPrime = grade.isProbablePrime(30);
            } while (!gradeIsPrime);

        boolean isGenerator;
        do {
            // How to efficient find a generator?
            generator = new BigInteger(4, r);
            // Choose an element g of Fp with order n
            isGenerator = generator.modPow(order, grade).equals(ONE);
        } while (!isGenerator && !generator.modPow(TWO, grade).equals(ONE));

    }

    @Override
    public Vote encrypt(BigInteger plain) {
        return null;
    }

    @Override
    public BigInteger decrypt(Vote vote) {
        return null;
    }

    public static void main(String[] args) {
        ElGamal gamal = new ElGamal();
    }
}

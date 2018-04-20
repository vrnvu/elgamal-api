# elgamal-api

 ElGamal implementations with the additive homomorphic property. 
 
 ## Table of content

- [Theroy](#theory)
    - [Key Generation](#key-generation)
    - [Encryption](#encryption)
    - [Decryption](#decryption)
- [Security](#security)
- [Efficiency](#efficiency)
- [Credits](#credits)
- [License](#license)



# Theory

ElGamal encryption consists of three components: the key generator, the encryption algorithm, and the decryption algorithm.

## Key Generation
  
  The key generator works as follows:
  
* Alice generates an efficient description of a cyclic group G of order q with generator g. See below for a discussion on the required properties of this group.
* Alice chooses an x randomly from { 1 , … , q − 1 } .
* Alice computes h:=g^x.
* Alice publishes h , along with the description of G , q , g as her public key. Alice retains x as her private key, which must be kept secret.
  

  
## Encryption

The encryption algorithm works as follows: to encrypt a message mto Alice under her public key ( G , q , g , h ).

* Bob chooses a random y from { 1 , … , q − 1 }, then calculates c1 :=g^y.
* Bob calculates the shared secret s:=h^y:=g^xy.
* Bob maps his message m onto an element m′ of G.
* Bob calculates c2:= m's.
* Bob sends the ciphertext ( c1 , c2 ) = ( g y , m ′ ⋅ h y ) = ( g y , m ′ ⋅ g x y ) to Alice.

Note that one can easily find h^y if one knows m'. Therefore, a new y is generated for every message to improve security. For this reason, y  is also called an ephemeral key.

## Decryption

The decryption algorithm works as follows: to decrypt a ciphertext ( c1 , c2 ) with her private key x,

* Alice calculates the shared secret s:=c1^x
* Alice computes m':=c2 s^-1 which she then converts back into the plaintext message m, where s^-1 is the inverse of s in the group G. 

# Efficiency

The division by s can be avoided by using an alternative method for decryption. To decrypt a ciphertext ( c1 , c  ) with Alice's private key x ,

Alice calculates s'= c1 ^ (q-x) = g ^ ((q-x)y).

s' is the inverse of s. This is a consequence of Lagrange's theorem.

# Credits

Arnau Díaz

# License

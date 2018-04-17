# elgamal-api

Descentralized voting application, DAPP, using ethereum.
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
* Alice chooses an x {\displaystyle x} x randomly from { 1 , … , q − 1 } {\displaystyle \{1,\ldots ,q-1\}} \{1, \ldots, q-1\}.
* Alice computes h := g x {\displaystyle h:=g^{x}} h:=g^{x}.
* Alice publishes h {\displaystyle h\,} h\,, along with the description of G , q , g {\displaystyle G,q,g\,} G, q, g\,, as her public key. Alice retains x {\displaystyle x} x as her private key, which must be kept secret.
  

  
## Encryption

The encryption algorithm works as follows: to encrypt a message m {\displaystyle m} m to Alice under her public key ( G , q , g , h ) {\displaystyle (G,q,g,h)} (G,q,g,h),

* Bob chooses a random y {\displaystyle y} y from { 1 , … , q − 1 } {\displaystyle \{1,\ldots ,q-1\}} \{1, \ldots, q-1\}, then calculates c 1 := g y {\displaystyle c_{1}:=g^{y}} c_{1}:=g^{y}.
* Bob calculates the shared secret s := h y := g x y {\displaystyle s:=h^{y}:=g^{xy}} {\displaystyle s:=h^{y}:=g^{xy}} .
* Bob maps his message m {\displaystyle m} m onto an element m ′ {\displaystyle m'} m' of G {\displaystyle G} G.
* Bob calculates c 2 := m ′ ⋅ s {\displaystyle c_{2}:=m'\cdot s} c_{2}:=m'\cdot s.
* Bob sends the ciphertext ( c 1 , c 2 ) = ( g y , m ′ ⋅ h y ) = ( g y , m ′ ⋅ g x y ) {\textstyle (c_{1},c_{2})=(g^{y},m'\cdot h^{y})=(g^{y},m'\cdot g^{xy})} {\textstyle (c_{1},c_{2})=(g^{y},m'\cdot h^{y})=(g^{y},m'\cdot g^{xy})} to Alice.

Note that one can easily find h y {\displaystyle h^{y}} h^{y} if one knows m ′ {\displaystyle m'} m'. Therefore, a new y {\displaystyle y} y is generated for every message to improve security. For this reason, y {\displaystyle y} y is also called an ephemeral key.

## Decryption

The decryption algorithm works as follows: to decrypt a ciphertext ( c 1 , c 2 ) {\displaystyle (c_{1},c_{2})} (c_{1},c_{2}) with her private key x {\displaystyle x} x,

* Alice calculates the shared secret s := c 1 x {\displaystyle s:=c_{1}{}^{x}} s:=c_{1}{}^{x}
* Alice computes m ′ := c 2 ⋅ s − 1 {\displaystyle m':=c_{2}\cdot s^{-1}} m':=c_{2}\cdot s^{{-1}} which she then converts back into the plaintext message m {\displaystyle m} m, where s − 1 {\displaystyle s^{-1}} s^{-1} is the inverse of s {\displaystyle s} s in the group G {\displaystyle G} G. (E.g. modular multiplicative inverse if G {\displaystyle G} G is a subgroup of a multiplicative group of integers modulo n).

# Credits

# License
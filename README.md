# AES Project

# Instructions
- Execute AES.java 
- Enter 'e' for Encryption or 'd' for Decryption 

In case of Encryption 

1. Select any file from File explorer to encrypt it.
2. Select where do you want to save the encrypted file.
3. Copy the printed key to use it for decryption.

In case of decryption 

1. Select any encrypted file from File explorer to decrypt it.
2. Enter the key that used in encryption.
3. Select where do you want to save the file after the decryption.

# Implementation

- Keys Generator

> My implementation will use 128 bit key size. 
> **generateKey()** method will generate a random 16 byte key.


This statement used to make the key for AES Alg.
`Key s = new SecretKeySpec(key.getBytes(), "AES");`


- Encryption

`Cipher cipher = Cipher.getInstance("AES");`
`cipher.init(Cipher.ENCRYPT_MODE, s);`

↓

`Cipher.AES" -> "com.foo.AESCipher`

> The application calls the getInstance() factory methods of the Cipher engine class, which in turn asks the JCA framework to find the first provider instance that supports "AES". The framework consults each installed provider, and obtains the provider's instance of the Provider class. (Recall that the Provider class is a database of available algorithms.) The framework searches each provider, finally finding a suitable entry in CSP3. This database entry points to the implementation class com.foo.AESCipher which extends CipherSpi, and is thus suitable for use by the Cipher engine class. An instance of com.foo.AESCipher is created, and is encapsulated in a newly-created instance of javax.crypto.Cipher, which is returned to the application. When the application now does the init() operation on the Cipher instance, the Cipher engine class routes the request into the corresponding engineInit() backing method in the com.foo.AESCipher class.


`CipherInputStream cis = new CipherInputStream(inputFile, cipher);`




 



# References
https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html#AppA

https://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec_image_descriptions.html#example-of-how-application-retrieves-aes-cipher-intstance



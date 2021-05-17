# Claves SHA



### Comandos generar clave

Ejemplo:

```powershell
keytool -list -v -keystore <nombre_keystore> -alias <nombre_alias>
```



Mío: (no funciona)

```powershell
keytool -list -v -keystore %USERPROFILE%\.android\keystore.jks -alias tokichat
```





C:\Users\Alberto\.android\personal_keystore.jks

48zUmYsxE3KkRPvE



tokichat

48zUmYsxE3KkRPvE

## SÍÍÍÍÍÍÍÍÍÍ

```
C:\Windows\system32>keytool -list -v -alias tokichat -keystore %USERPROFILE%\.android\personal_keystore.jks
Introduzca la contraseña del almacén de claves:
Nombre de Alias: tokichat
Fecha de Creación: 17-may-2021
Tipo de Entrada: PrivateKeyEntry
Longitud de la Cadena de Certificado: 1
Certificado[1]:
Propietario: CN=Alberto Gonzalez, OU=TokiApps, O=TokiApps, L=Zaragoza, ST=Aragon, C=34
Emisor: CN=Alberto Gonzalez, OU=TokiApps, O=TokiApps, L=Zaragoza, ST=Aragon, C=34
Número de serie: 1248250
Válido desde: Mon May 17 22:01:05 CEST 2021 hasta: Fri May 11 22:01:05 CEST 2046
Huellas digitales del certificado:
         SHA1: B0:25:51:29:45:59:6D:56:E8:A0:FE:A8:92:3B:41:AF:5D:1C:C2:38
         SHA256: A7:63:EE:12:D3:55:28:14:49:F3:A0:94:96:26:2D:95:D6:3A:E7:27:A2:57:72:7F:9A:3B:24:E4:16:EF:00:CD
Nombre del algoritmo de firma: SHA256withRSA
Algoritmo de clave pública de asunto: Clave RSA de 2048 bits
Versión: 3

Extensiones:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 86 0E A7 99 17 A6 8F DE   05 2E FB E5 14 DF C0 85  ................
0010: AD 2A 3A C5                                        .*:.
]
]


```

---



La vida es bella
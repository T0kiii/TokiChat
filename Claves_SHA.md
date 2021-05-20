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



Segunda tanda:

```powershell
9:51:24 p. m.: Executing task 'signingReport'...

Executing tasks: [signingReport] in project C:\Users\agonzalezger\Documents\mis_cosas\TokiChat

AGPBI: {"kind":"warning","text":"Please remove usages of `jcenter()` Maven repository from your build scripts and migrate your build to other Maven repositories.\nThis repository is deprecated and it will be shut down in the future.\nSee http://developer.android.com/r/tools/jcenter-end-of-service for more information.\nCurrently detected usages in: root project 'TokiChat', project ':app'","sources":[{}]}

> Task :app:signingReport
Variant: debug
Config: debug
Store: C:\Users\agonzalezger\.android\debug.keystore
Alias: AndroidDebugKey
MD5: A9:16:13:9B:D6:DB:2A:6A:FE:14:33:58:64:6F:4F:C1
SHA1: 16:5B:6E:83:31:B3:79:31:0B:11:71:A1:21:F0:14:33:89:D6:41:9A
SHA-256: 81:76:05:DA:83:A2:6A:13:23:D7:8B:E9:7A:5A:B8:C0:BB:41:9A:4E:29:ED:54:E0:01:09:2C:23:3D:78:FE:9B
Valid until: viernes, 12 de mayo de 2051
----------
Variant: release
Config: none
----------
Variant: debugAndroidTest
Config: debug
Store: C:\Users\agonzalezger\.android\debug.keystore
Alias: AndroidDebugKey
MD5: A9:16:13:9B:D6:DB:2A:6A:FE:14:33:58:64:6F:4F:C1
SHA1: 16:5B:6E:83:31:B3:79:31:0B:11:71:A1:21:F0:14:33:89:D6:41:9A
SHA-256: 81:76:05:DA:83:A2:6A:13:23:D7:8B:E9:7A:5A:B8:C0:BB:41:9A:4E:29:ED:54:E0:01:09:2C:23:3D:78:FE:9B
Valid until: viernes, 12 de mayo de 2051
----------
Variant: debugUnitTest
Config: debug
Store: C:\Users\agonzalezger\.android\debug.keystore
Alias: AndroidDebugKey
MD5: A9:16:13:9B:D6:DB:2A:6A:FE:14:33:58:64:6F:4F:C1
SHA1: 16:5B:6E:83:31:B3:79:31:0B:11:71:A1:21:F0:14:33:89:D6:41:9A
SHA-256: 81:76:05:DA:83:A2:6A:13:23:D7:8B:E9:7A:5A:B8:C0:BB:41:9A:4E:29:ED:54:E0:01:09:2C:23:3D:78:FE:9B
Valid until: viernes, 12 de mayo de 2051
----------
Variant: releaseUnitTest
Config: none
----------

BUILD SUCCESSFUL in 2s
1 actionable task: 1 executed
9:51:26 p. m.: Task execution finished 'signingReport'.
```


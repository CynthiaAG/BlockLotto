# App BlockLotto

## ¿Qué es BlockLotto?
BlockLotto es un proyecto de final de título (TFT) del Grado en Ingeniería Informática de la Universidad de Las Palmas de Gran Canaria. 

El objetivo principal de este proyecto es el desarrollo de una app wallet para realizar
apuestas de lotería utilizando la tecnología Lightning para realizar micropagos. Esta
tecnología permite al usuario comprar directamente un número desde el wallet, así como
recibir los premios. El wallet se implementará considerando la siguiente arquitectura:

![esquema](https://github.com/CynthiaAG/BlockLotto/blob/master/esquema.png)

Además de desarrollar el LottoWallet, se integrará con el LottoService, un servicio web que
se desarrollará como objetivo de otro TFT. Por tanto, este proyecto se realiza de forma
conjunta con otro estudiante. 

Funcionalmente, la aplicación permitirá a un usuario:
1. Ver los sorteos que próximamente se celebrarán.
2. Transferir fondos al wallet desde una cuenta de bitcoin o un exchange
3. Comprar un número de lotería para un sorteo. Para ello, tendrá que transferir dinero
hacia el LottoWallet. Una vez esta transacción se confirme, podrá ver el número de
lotería que ha comprado para participar en un sorteo.
4. Ver los sorteos que ya se hayan celebrado, y en especial en los que ha participado el usuario, y los números agraciados.
5. Cobrar el premio. El usuario ganador de la lotería podrá recibir el premio en el
LottoWallet.
6. Transferir los premios a una cuenta de bitcoin o un exchange.

Asimismo, la app notificará al usuario si ha ganado en las loterías que ha jugado.

##### Descargar APK [aquí](https://github.com/CynthiaAG/BlockLotto/tree/master/APK)

## Tecnología utilizada
* Android Studio 
* Versión de Android utilizada:
  + **Versión:** 6.0
  + **CodeName:** Marshmallow
  + **API:** 23

## Equipo

* **Tutorizado por:** José Juan Hernández Cabrera
* **Desarrollado por:** Cynthia Judith Afonso García
* **Desarrollo del servicio LottoService (colaboración):** [Jorge Fernández Molines](https://github.com/kovutech/BlockLotto)

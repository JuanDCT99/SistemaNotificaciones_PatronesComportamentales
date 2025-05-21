# Sistema de Notificaciones con Patrones de Comportamiento

## Descripción

Este proyecto implementa un sistema de notificaciones utilizando patrones de comportamiento en Java. El sistema permite enviar notificaciones a través de diferentes canales (Email, SMS, Push) y gestionar usuarios y sus preferencias de notificación.

## Patrones de Comportamiento Implementados

### Patrón Observer

El patrón Observer permite que múltiples objetos (observadores) sean notificados automáticamente cuando ocurre un cambio en el objeto observado.

- **Interfaz Observador**: Define el método `actualizar` que será llamado cuando ocurra un cambio
- **Clase EventManager**: Mantiene una lista de observadores y los notifica cuando ocurre un evento
- **Clase User**: Implementa la interfaz Observador y recibe notificaciones

### Patrón Strategy

El patrón Strategy permite cambiar el algoritmo de envío de notificaciones en tiempo de ejecución.

- **Interfaz NotificationStrategy**: Define el método `enviarNotificacion`
- **Clases concretas**: EmailNotification, SMSNotification, PushNotification
- **Clase User**: Utiliza una estrategia de notificación que puede cambiar en tiempo de ejecución

### Patrón Template Method

El patrón Template Method define el esqueleto de un algoritmo, permitiendo que las subclases redefinan ciertos pasos.

- **Clase abstracta User**: Define el método template `actualizar` que utiliza el método abstracto `formatearMensaje`
- **Subclases ClientUser y AdminUser**: Implementan el método `formatearMensaje` de manera específica

## Estructura del Proyecto

```
SistemaNotificaciones/
├── docs/
│   └── ManualUsuario.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── co/edu/uniquindio/poo/sistemanotificaciones/
│   │   │       ├── Controller/
│   │   │       │   └── AppController.java
│   │   │       ├── Model/
│   │   │       │   ├── AdminUser.java
│   │   │       │   ├── ClientUser.java
│   │   │       │   ├── EmailNotification.java
│   │   │       │   ├── EventManager.java
│   │   │       │   ├── NotificationHistory.java
│   │   │       │   ├── NotificationPreferences.java
│   │   │       │   ├── NotificationStrategy.java
│   │   │       │   ├── Observador.java
│   │   │       │   ├── PushNotification.java
│   │   │       │   ├── SMSNotification.java
│   │   │       │   └── User.java
│   │   │       ├── Service/
│   │   │       │   ├── PersistenceService.java
│   │   │       │   └── UserService.java
│   │   │       └── ViewController/
│   │   │           ├── BienvenidaViewController.java
│   │   │           ├── ConfiguracionNotificacionesViewController.java
│   │   │           ├── Email_ViewController.java
│   │   │           ├── EntradaUsuarioViewController.java
│   │   │           ├── HistorialNotificacionesViewController.java
│   │   │           ├── informacionProgramaViewController.java
│   │   │           ├── Push_ViewController.java
│   │   │           └── SMS_ViewController.java
│   │   └── resources/
│   │       └── co/edu/uniquindio/poo/sistemanotificaciones/
│   │           ├── Bienvenida.fxml
│   │           ├── ConfiguracionNotificaciones.fxml
│   │           ├── Email.fxml
│   │           ├── EntradaUsuario.fxml
│   │           ├── HistorialNotificaciones.fxml
│   │           ├── InformacionPrograma.fxml
│   │           ├── Push.fxml
│   │           └── SMS.fxml
│   └── test/
│       └── java/
│           └── co/edu/uniquindio/poo/sistemanotificaciones/
│               └── Model/
│                   ├── NotificationPreferencesTest.java
│                   ├── NotificationStrategyTest.java
│                   └── ObserverPatternTest.java
└── README.md
```

## Requisitos

- Java 11 o superior
- JavaFX 11 o superior
- Maven 3.6 o superior

## Instalación y Ejecución

1. Clone el repositorio:
   ```
   git clone https://github.com/usuario/SistemaNotificaciones.git
   ```

2. Navegue al directorio del proyecto:
   ```
   cd SistemaNotificaciones
   ```

3. Compile el proyecto con Maven:
   ```
   mvn clean package
   ```

4. Ejecute la aplicación:
   ```
   java -jar target/SistemaNotificaciones-1.0.jar
   ```

## Uso

Consulte el [Manual de Usuario](docs/ManualUsuario.md) para obtener instrucciones detalladas sobre cómo usar el sistema.

## Pruebas

Para ejecutar las pruebas unitarias:
```
mvn test
```

## Contribución

1. Haga un fork del repositorio
2. Cree una rama para su funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Haga commit de sus cambios (`git commit -am 'Añadir nueva funcionalidad'`)
4. Haga push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Cree un Pull Request

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - vea el archivo LICENSE para más detalles.

## Autor

- Nombre del Autor - [correo@ejemplo.com](mailto:correo@ejemplo.com)

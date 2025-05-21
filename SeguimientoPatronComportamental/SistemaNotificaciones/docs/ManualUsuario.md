# Manual de Usuario - Sistema de Notificaciones

## Introducción

El Sistema de Notificaciones es una aplicación que permite gestionar y enviar notificaciones a través de diferentes canales (Email, SMS, Push). El sistema implementa tres patrones de comportamiento:

1. **Patrón Observer**: Para notificar a los usuarios suscritos
2. **Patrón Strategy**: Para seleccionar diferentes métodos de notificación
3. **Patrón Template Method**: Para personalizar el formato de los mensajes según el tipo de usuario

## Requisitos del Sistema

- Java 11 o superior
- JavaFX 11 o superior

## Instalación

1. Descargue el archivo JAR del sistema desde el repositorio
2. Ejecute el archivo JAR con el comando: `java -jar SistemaNotificaciones.jar`

## Funcionalidades Principales

### Registro e Inicio de Sesión

1. Al iniciar la aplicación, se mostrará la pantalla de bienvenida
2. Haga clic en el botón "Iniciar" para acceder al menú principal
3. En el menú principal, haga clic en "Registro/Inicio Sesión"
4. Para registrarse:
   - Complete los campos de nombre, correo y contraseña en la sección derecha
   - Haga clic en "Registrar"
5. Para iniciar sesión:
   - Ingrese su correo y contraseña en la sección izquierda
   - Haga clic en "Iniciar Sesión"

### Envío de Notificaciones

Una vez iniciada la sesión, puede enviar notificaciones a través de diferentes canales:

#### Email

1. Haga clic en el botón "Email" en el menú principal
2. Complete los campos:
   - Para: Dirección de correo del destinatario
   - Asunto: Asunto del correo
   - Mensaje: Contenido del correo
3. Haga clic en "Enviar" para enviar la notificación
4. Haga clic en "Volver" para regresar al menú principal

#### SMS

1. Haga clic en el botón "SMS" en el menú principal
2. Complete los campos:
   - Enviado de: Número de teléfono del remitente
   - Recibido por: Número de teléfono del destinatario
   - Mensaje: Contenido del SMS
3. Haga clic en "Enviar SMS" para enviar la notificación
4. Haga clic en "Volver" para regresar al menú principal

#### Push

1. Haga clic en el botón "Push" en el menú principal
2. Complete los campos:
   - Celular: Número de teléfono del destinatario
   - Mensaje: Contenido de la notificación push
3. Haga clic en "Enviar" para enviar la notificación
4. Haga clic en "Volver" para regresar al menú principal

### Historial de Notificaciones

Para ver el historial de notificaciones enviadas:

1. Haga clic en "Ver Historial de Notificaciones" en el menú principal
2. Se mostrará una tabla con todas las notificaciones enviadas
3. Puede filtrar las notificaciones por tipo (Email, SMS, Push) usando el combo box
4. Haga clic en "Filtrar" para aplicar el filtro
5. Haga clic en "Limpiar Filtro" para mostrar todas las notificaciones
6. Haga clic en "Volver al Menú Principal" para regresar

### Configuración de Notificaciones

Para configurar sus preferencias de notificación:

1. Haga clic en "Configuración de Notificaciones" en el menú principal
2. Configure sus preferencias:
   - Método de notificación preferido: Email, SMS o Push
   - Información de contacto: Email y teléfono
   - Preferencias de notificación: Recibir todas o solo urgentes
3. Haga clic en "Guardar Configuración" para guardar los cambios
4. Haga clic en "Cancelar" para descartar los cambios y volver al menú principal

### Envío de Notificaciones a Todos los Usuarios

Si tiene permisos de administrador, puede enviar notificaciones a todos los usuarios:

1. Haga clic en "Enviar Notificación a Todos" en el menú principal
2. Ingrese el mensaje que desea enviar
3. Haga clic en "Aceptar" para enviar la notificación a todos los usuarios

## Patrones de Comportamiento Implementados

### Patrón Observer

El patrón Observer permite que múltiples objetos (observadores) sean notificados automáticamente cuando ocurre un cambio en el objeto observado. En el sistema:

- Los usuarios son observadores
- El EventManager es el sujeto observado
- Cuando se envía una notificación, todos los usuarios suscritos la reciben

### Patrón Strategy

El patrón Strategy permite cambiar el algoritmo de envío de notificaciones en tiempo de ejecución. En el sistema:

- NotificationStrategy es la interfaz de estrategia
- EmailNotification, SMSNotification y PushNotification son las estrategias concretas
- Los usuarios pueden cambiar su estrategia de notificación en cualquier momento

### Patrón Template Method

El patrón Template Method define el esqueleto de un algoritmo, permitiendo que las subclases redefinan ciertos pasos. En el sistema:

- User es la clase abstracta con el método template
- ClientUser y AdminUser son las subclases que implementan el método formatearMensaje
- El método actualizar utiliza el método formatearMensaje para personalizar las notificaciones

## Soporte

Si tiene problemas o preguntas, contacte al administrador del sistema.

# 🏦 Sistema de Gestión Bancaria 

Este proyecto implementa un sistema robusto de gestión de activos financieros, desarrollado para profundizar en los pilares avanzados de la **Programación Orientada a Objetos** en Java. Se enfoca en la creación de una jerarquía de clases capaz de gestionar reglas de negocio diferenciadas para diversos productos bancarios.

El proyecto destaca por la implementación de conceptos de ingeniería de software que garantizan la escalabilidad y el mantenimiento del código:

- **Abstracción y Herencia:** Uso de una clase base abstracta `CuentaBancaria` que define el contrato de comportamiento para las especializaciones `CuentaCorriente` y `CuentaAhorro`.
- **Interfaces (Desacoplamiento):** Implementación de la interfaz `Transaccion` para estandarizar operaciones críticas (transferencias y consultas), permitiendo que diferentes tipos de cuentas interactúen de forma segura.
- **Polimorfismo:** Procesamiento uniforme de diferentes tipos de cuenta a través de un único array de la clase madre, facilitando la gestión masiva de datos.
- **Casteo y Validación de Tipos:** Uso de `instanceof` y *casting* para manejar reglas específicas, como restringir transferencias solo entre cuentas de ahorro cuando la normativa lo requiere.

## 💼 Reglas de Negocio Implementadas
- **Cuenta Corriente:** Gestión de **Límite de Riesgo** (permite saldos negativos controlados) y validación de depósitos máximos.
- **Cuenta Ahorro:** Lógica de **bloqueo temporal** mediante `numMeses`. El sistema impide retiros o depósitos si no se cumplen las condiciones de plazo.
- **Robustez en Transferencias:** Motor de transacciones que verifica la solvencia del origen y la compatibilidad del destino antes de ejecutar cualquier movimiento de capital.

## 💻 Stack y Herramientas
- **Lenguaje:** Java 17+ ☕
- **Gestión de Errores:** Implementación de bloques `try-catch` con `InputMismatchException` para una entrada de datos a prueba de fallos.
- **Simulación:** Generador aleatorio de identidades (basado en el lore de Warhammer 40K) para pruebas de entorno real.

## 📂 Estructura de Clases Clave
- `CuentaBancaria.java`: Clase abstracta que define el núcleo del sistema.
- `CuentaCorriente.java` & `CuentaAhorro.java`: Clases derivadas con lógica específica de producto.
- `Transaccion.java`: Interfaz que actúa como contrato para movimientos financieros.
- `Ejer7_main.java`: Orquestador principal con gestión de menús y validaciones de usuario.


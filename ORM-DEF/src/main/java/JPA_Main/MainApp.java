package JPA_Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import hybernates.ORM_DEF.ExampleCrud;


public class MainApp { //Classe d'entrada del projecte.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		ExampleCrud Crud;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		
		
		while (opcion!=5) {
			System.out.println("1. Crear");
			System.out.println("2. Buscar");
			System.out.println("3. Actualizar");
			System.out.println("4. Eliminar");
			System.out.println("5. Salir");
			System.out.print("Escull una opció:");

			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.print("\nPosa el nom:");
				Crud = new ExampleCrud();
				Crud.setId(null);
				scanner.nextLine();
				Crud.setNombre(scanner.nextLine());

				System.out.print("\nDigite el precio del producto:");
				Crud.setPrecio(scanner.nextDouble());
				System.out.println(Crud);
				entity.getTransaction().begin();
				entity.persist(Crud);
				entity.getTransaction().commit();
				System.out.println("Producto registrado..");
				System.out.println();
				break;

			case 2:
				System.out.print("\nDigite el id del producto a buscar:");
				Crud = new ExampleCrud();
				Crud = entity.find(ExampleCrud.class, scanner.nextLong());
				if (Crud != null) {
					System.out.println(Crud);
					System.out.println();
				} else {
					System.out.println();
					System.out.println("Producto no encontrado... Lista de productos completa");
					List<ExampleCrud> listaProductos= new ArrayList<>();
					Query query=entity.createQuery("SELECT p FROM Producto p");
					listaProductos=query.getResultList();
					for (ExampleCrud p : listaProductos) {
						System.out.println(p);
					}
					
					System.out.println();
				}

				break;
			case 3:
				System.out.print("\nDigite el id del producto a actualizar:");
				Crud = new ExampleCrud();

				Crud = entity.find(ExampleCrud.class, scanner.nextLong());
				if (Crud != null) {
					System.out.println(Crud);
					System.out.println("Digite el nombre del producto:");
					scanner.nextLine();
					Crud.setNombre(scanner.nextLine());
					System.out.println("Digite el precio del producto:");
					Crud.setPrecio(scanner.nextDouble());
					entity.getTransaction().begin();
					entity.merge(Crud);
					entity.getTransaction().commit();
					System.out.println("Producto actualizado..");
					System.out.println();
				} else {
					System.out.println("Producto no encontrado....");
					System.out.println();
				}
				break;
			case 4:
				System.out.print("\nDigite el id del producto a eliminar:");
				Crud = new ExampleCrud();

				Crud = entity.find(ExampleCrud.class, scanner.nextLong());
				if (Crud != null) {
					System.out.println(Crud);
					entity.getTransaction().begin();
					entity.remove(Crud);
					entity.getTransaction().commit();
					System.out.println("Producto eliminado...");
				} else {
					System.out.println("Producto no encontrado...");
				}
				break;
			case 5:entity.close();JPAUtil.shutdown();
			break;

			default:
				System.out.println("Opción no válida\n");
				break;
			}
		}
		
		
	}

}

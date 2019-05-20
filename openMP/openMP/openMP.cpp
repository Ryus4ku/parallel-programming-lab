#include "pch.h"
#include <iostream>
#include <time.h>
#include <omp.h>
using namespace std;

constexpr auto MAX_THREAD = 4;
constexpr auto MAX_NUMBERS = 99999999;

int a[MAX_NUMBERS];
int part = 0;
int max = 0;
int min = 0;

void maxMin() {
#pragma omp parallel for 
	for (int i = 0; i < sizeof(a) / sizeof(a[i]); i++) {
		max = a[i] > max ? a[i] : max;
		min = (min == 0) ? a[i] : (a[i] < min ? a[i] : min);
	}
}

int main() {
	setlocale(LC_ALL, "Rus");
	clock_t time = clock();
	cout << "Таймер запущен.\nЗаполнение массива числами (" << MAX_NUMBERS << ").\n";

	int myid;
	int i;
#pragma omp parallel private(myid)
	{
		myid = omp_get_thread_num();
#pragma omp parallel for private(i)
		for (i = 0; i < 1; i++) {
			printf("T%d: %d\n", myid, i);
		}
	}

#pragma omp parallel for
	for (int i = 0; i < MAX_NUMBERS; i++) {
		a[i] = rand() * (rand() - rand()) + 5;
	};

	cout << "Массив заполнен!\nПоиск Максимального и минимального чисел...\n";
	maxMin();
	printf("Максимальное число - %d\nМинимальное число - %d\n", max, min);
	time = clock() - time;
	printf("%f сек.\nТаймер остоновлен.", (double) time / CLOCKS_PER_SEC);
	return 0;
}

// Запуск программы: CTRL+F5 или меню "Отладка" > "Запуск без отладки"
// Отладка программы: F5 или меню "Отладка" > "Запустить отладку"

// Советы по началу работы 
//   1. В окне обозревателя решений можно добавлять файлы и управлять ими.
//   2. В окне Team Explorer можно подключиться к системе управления версиями.
//   3. В окне "Выходные данные" можно просматривать выходные данные сборки и другие сообщения.
//   4. В окне "Список ошибок" можно просматривать ошибки.
//   5. Последовательно выберите пункты меню "Проект" > "Добавить новый элемент", чтобы создать файлы кода, или "Проект" > "Добавить существующий элемент", чтобы добавить в проект существующие файлы кода.
//   6. Чтобы снова открыть этот проект позже, выберите пункты меню "Файл" > "Открыть" > "Проект" и выберите SLN-файл.
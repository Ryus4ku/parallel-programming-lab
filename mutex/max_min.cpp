#include <stdio.h>
#include <pthread.h>
#include <time.h>

#define MAX_THREAD 1;

int a[] = {430, 730, 898, 631, 441, 314, 321, 563, 841, 385, 477, 246, 916, 31, 545, 19};
int part = 0;
int max = 0;
int min = 0;

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void* maxMin_array(void* arg) {
    int thread_part = part++;
    for(int i = 0; i < sizeof(a)/sizeof(a[i]); i++) {
        if (a[i] > max || a[i] < min) {
            pthread_mutex_lock(&mutex);
                max = a[i] > max ? a[i] : max;
                min = (min == 0) ? a[i] : (a[i] < min ? a[i] : min);
            pthread_mutex_unlock(&mutex);
        }
    }
}

int main() {
    clock_t time;
    time = clock();
    pthread_t threads[MAX_THREAD];
    for(int i = 0; i < MAX_THREAD; i++) {
        pthread_create(&threads[i], NULL, maxMin_array, (void*)NULL);
    }

    for(int i = 0; i < MAX_THREAD; i++) {
        pthread_join(threads[i], NULL);
    }

    printf("Max is %d\nMin is %d\n", max, min);
    pthread_mutex_destroy(&mutex);
    time = clock() - time;
    printf("%f\n", (double) time / CLOCKS_PER_SEC);
    return 0;    
}
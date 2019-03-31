#include <iostream>
#include <ctime>

class TIMER {
    private:
        std::string name_;
        std::clock_t start_;
    public: 
        TIMER(const std::string& name) : name_ (name), start_ (std::clock()) {}
        ~TIMER(){
            double elapsed = (double(std::clock() - start_) / double(CLOCKS_PER_SEC));
            std::cout << name_ << ": " << int(elapsed * 1000) << "ms" << std::endl;
        }
};
#define TIMER(name) TIMER timer__(name);